package com.mkraskiewicz.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Maciej on 09/07/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "Name of the Vendor.", required = true)
    private String name;

    @ApiModelProperty(value = "Vendors url.")
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
