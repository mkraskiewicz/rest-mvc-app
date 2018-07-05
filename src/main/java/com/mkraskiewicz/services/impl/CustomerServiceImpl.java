package com.mkraskiewicz.services.impl;

import com.mkraskiewicz.api.v1.mapper.CustomerMapper;
import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.domain.Customer;
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
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return  customerDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerMapper.customerToCustomerDTO(customerRepository.findById(id)
                .orElseThrow(RuntimeException::new));

    }

    @Override
    public CustomerDTO crateNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customerToSave) {
        Customer savedCustomer = customerRepository.save(customerToSave);

        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnedDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnedDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return  saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }
            return  customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        }).orElseThrow(RuntimeException::new);
    }

}
