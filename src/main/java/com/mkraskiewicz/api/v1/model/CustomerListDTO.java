package com.mkraskiewicz.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Maciej on 04/07/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {

    List<CustomerDTO> customers;
}
