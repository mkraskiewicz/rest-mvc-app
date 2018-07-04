package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.api.v1.model.CustomerListDTO;
import com.mkraskiewicz.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created by Maciej on 04/07/2018
 */
@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable("id") Long id){

        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id),
                HttpStatus.OK);
    }

}
