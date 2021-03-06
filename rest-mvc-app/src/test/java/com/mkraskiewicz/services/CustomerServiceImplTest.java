package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.mapper.CustomerMapper;
import com.mkraskiewicz.controllers.v1.CustomerController;
import com.mkraskiewicz.domain.Customer;
import com.mkraskiewicz.model.CustomerDTO;
import com.mkraskiewicz.repositories.CustomerRepository;
import com.mkraskiewicz.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej on 04/07/2018
 */
public class CustomerServiceImplTest {



    public static final String FIRST_NAME = "Maciej";
    public static final String LAST_NAME = "Kraskiewicz";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {

        //given
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);

        //when
        List<CustomerDTO> receivedList = customerService.getAllCustomers();

        //then
        assertEquals(3, receivedList.size());

    }

    @Test
    public void getCustomerById() {

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(anyLong());

        //then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

    @Test
    public void saveCustomerTest(){

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(Long.valueOf(1));

        CustomerDTO givenDTO = new CustomerDTO();
        givenDTO.setLastName(customer.getLastName());
        givenDTO.setFirstName(customer.getFirstName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        //when
        CustomerDTO returnedDTO = customerService.crateNewCustomer(givenDTO);

        //then
        assertEquals(FIRST_NAME, returnedDTO.getFirstName());
        assertEquals(LAST_NAME, returnedDTO.getLastName());
        assertEquals(CustomerController.BASE_URL +  "/1", returnedDTO.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO(){

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(Long.valueOf(1));

        CustomerDTO givenDTO = new CustomerDTO();
        givenDTO.setLastName(customer.getLastName());
        givenDTO.setFirstName(customer.getFirstName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);


        //when
        CustomerDTO returnedDTO = customerService.saveCustomerByDTO(Long.valueOf(1),givenDTO);

        //then
        assertEquals(FIRST_NAME, returnedDTO.getFirstName());
        assertEquals(LAST_NAME, returnedDTO.getLastName());
        assertEquals(CustomerController.BASE_URL +  "/1", returnedDTO.getCustomerUrl());
    }

    @Test
    public void patchCustomerTest(){

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(Long.valueOf(1));

        CustomerDTO givenDTO = new CustomerDTO();
        givenDTO.setLastName(customer.getLastName());
        givenDTO.setFirstName(customer.getFirstName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when

        CustomerDTO savedDTO = customerService.patchCustomer(Long.valueOf(1), givenDTO);

        //then

        verify(customerRepository, times(1)).findById(anyLong());
        assertEquals(savedDTO.getCustomerUrl(), savedDTO.getCustomerUrl());
    }
    @Test
    public void deleteCustomerByIdTest() throws Exception {

        Long id = Long.valueOf(1);

        customerService.deleteCustomerById(id);

        verify(customerRepository, timeout(1)).deleteById(anyLong());
    }
}