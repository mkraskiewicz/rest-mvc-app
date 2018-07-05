package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Maciej on 04/07/2018
 */
public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO crateNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
