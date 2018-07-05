package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.api.v1.model.CustomerListDTO;
import com.mkraskiewicz.domain.Customer;
import com.mkraskiewicz.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Maciej on 04/07/2018
 */
@Controller
@RequestMapping("/api/v1/customers")
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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable("id") Long id){

        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.crateNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }

}
