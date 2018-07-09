package com.mkraskiewicz.services.impl;

import com.mkraskiewicz.api.v1.mapper.VendorMapper;
import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.controllers.v1.VendorController;
import com.mkraskiewicz.domain.Vendor;
import com.mkraskiewicz.exceptions.ResourceNotFoundException;
import com.mkraskiewicz.repositories.VendorRepository;
import com.mkraskiewicz.services.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maciej on 09/07/2018
 */
@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {

        return vendorRepository.findAll().stream().map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getCustomerUrl(vendor.getId()));
            return vendorDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        return  vendorRepository.findById(id).map(vendor ->{
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getCustomerUrl(id));
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendorToSave){

        Vendor savedVendor = vendorRepository.save(vendorToSave);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setVendorUrl(getCustomerUrl(savedVendor.getId()));

        return vendorDTO;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

       return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTOToSave) {

        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTOToSave);
        vendorToSave.setId(id);

        return  saveAndReturnDTO(vendorToSave);

    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null ){
                vendor.setName(vendorDTO.getName());
            }

            return saveAndReturnDTO(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {

        vendorRepository.deleteById(id);
    }

    private String getCustomerUrl(Long id){

        return VendorController.BASE_URL + "/" + id;
    }
}
