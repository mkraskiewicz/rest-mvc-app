package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.model.VendorDTO;

import java.util.List;

/**
 * Created by Maciej on 09/07/2018
 */
public interface VendorService {

    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);

}
