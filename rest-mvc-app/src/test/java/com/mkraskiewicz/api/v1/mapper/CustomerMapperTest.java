package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.domain.Customer;
import com.mkraskiewicz.model.CustomerDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maciej on 04/07/2018
 */
public class CustomerMapperTest {

    public static final String FIRST_NAME = "Maciej";
    public static final String LAST_NAME = "Kraskiewicz";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then

        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());

    }

    @Test
    public void customerDTOToCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        //when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        //then
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
    }
}