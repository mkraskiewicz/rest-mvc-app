package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.mapper.CustomerMapper;
import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.bootstrap.Bootstrap;
import com.mkraskiewicz.domain.Customer;
import com.mkraskiewicz.repositories.CategoryRepository;
import com.mkraskiewicz.repositories.CustomerRepository;
import com.mkraskiewicz.repositories.VendorRepository;
import com.mkraskiewicz.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Maciej on 05/07/2018
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    static final  String UPDATED_VALUE = "Updated String";

    CustomerService customerService;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {

        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }


    @Test
    public void patchCustomerFirstName(){

       Long id = getCustomerValue();

       Customer originalCustomer = customerRepository.getOne(id);
       assertNotNull(originalCustomer);

       String originalFirstName = originalCustomer.getFirstName();
       String originalLastName = originalCustomer.getLastName();

       CustomerDTO customerDTO = new CustomerDTO();
       customerDTO.setFirstName(UPDATED_VALUE);

       customerService.patchCustomer(id, customerDTO);

       Customer updatedCustomer = customerRepository.getOne(id);

       assertNotNull(updatedCustomer);
       assertEquals(UPDATED_VALUE, updatedCustomer.getFirstName());
       assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
       assertEquals(originalLastName, updatedCustomer.getLastName());
    }

    @Test
    public void patchCustomerLastName(){

        Long id = getCustomerValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(UPDATED_VALUE);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.getOne(id);

        assertNotNull(updatedCustomer);
        assertEquals(UPDATED_VALUE, updatedCustomer.getLastName());
        assertNotEquals(originalLastName, updatedCustomer.getLastName());
        assertEquals(originalFirstName, updatedCustomer.getFirstName());
    }

    private Long getCustomerValue(){

        List<Customer> customerList = customerRepository.findAll();
        return customerList.get(0).getId();
    }

}
