package com.mkraskiewicz.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Maciej on 04/07/2018
 */
@Data
public class CustomerDTO {

    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
