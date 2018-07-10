package com.mkraskiewicz.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Maciej on 04/07/2018
 */
@Data
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name.", required = true)
    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
