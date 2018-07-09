package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maciej on 09/07/2018
 */
public class VendorMapperTest {

    private static final String NAME = "VENDOR_TEST";
    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {

        //given
        Vendor givenVendor = new Vendor();
        givenVendor.setName(NAME);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(givenVendor);

        //then
        assertEquals(NAME, vendorDTO.getName());


    }

    @Test
    public void vendorDTOTOVendor() {

        //given
        VendorDTO givenVendorDTO = new VendorDTO();
        givenVendorDTO.setName(NAME);

        //when
        Vendor vendor = vendorMapper.vendorDTOToVendor(givenVendorDTO);

        //then
        assertEquals(NAME, vendor.getName());
    }
}