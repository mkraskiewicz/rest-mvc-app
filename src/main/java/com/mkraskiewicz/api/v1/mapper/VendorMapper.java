package com.mkraskiewicz.api.v1.mapper;

import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Maciej on 09/07/2018
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
