package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Maciej on 04/07/2018
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
