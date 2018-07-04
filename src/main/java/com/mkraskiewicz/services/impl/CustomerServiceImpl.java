package com.mkraskiewicz.services.impl;

import com.mkraskiewicz.api.v1.mapper.CustomerMapper;
import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.repositories.CustomerRepository;
import com.mkraskiewicz.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maciej on 04/07/2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return  customerDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerMapper.customerToCustomerDTO(customerRepository.findById(id)
                .orElseThrow(RuntimeException::new));

    }
}
