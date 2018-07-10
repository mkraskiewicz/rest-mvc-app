package com.mkraskiewicz.services;

import com.mkraskiewicz.api.v1.mapper.VendorMapper;
import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.controllers.v1.VendorController;
import com.mkraskiewicz.domain.Vendor;
import com.mkraskiewicz.repositories.VendorRepository;
import com.mkraskiewicz.services.impl.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej on 09/07/2018
 */
public class VendorServiceImplTest {


    public static final String NAME = "Maciej";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendorsTest() {

        //given
        List<Vendor> vendorList = Arrays.asList(new Vendor(), new Vendor(), new Vendor() );
        when(vendorRepository.findAll()).thenReturn(vendorList);

        //when
        List<VendorDTO> returnedVendorList = vendorService.getAllVendors();

        //then
        assertEquals(3, returnedVendorList.size());
    }

    @Test
    public void getVendorByIdTest() {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when
        VendorDTO returnedVendorDTO = vendorService.getVendorById(anyLong());

        //then
        assertEquals(NAME, returnedVendorDTO.getName());
    }

    @Test
    public void createNewVendorTest() {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(Long.valueOf(1));

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO createdVendor = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(NAME, createdVendor.getName());
        assertEquals(VendorController.BASE_URL + "/1", createdVendor.getVendorUrl());

    }

    @Test
    public void saveVendorByDTOTest() {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(Long.valueOf(1));

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO createdVendor = vendorService.saveVendorByDTO(Long.valueOf(1),vendorDTO);

        //then
        assertEquals(NAME, createdVendor.getName());
        assertEquals(VendorController.BASE_URL + "/1", createdVendor.getVendorUrl());

    }


    @Test
    public void patchVendorTest(){

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(Long.valueOf(1));

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when

        VendorDTO savedDTO = vendorService.patchVendor(Long.valueOf(1), vendorDTO);

        //then

        verify(vendorRepository, times(1)).findById(anyLong());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void deleteVendorByIdTest() {

        Long id = Long.valueOf(1);

        vendorService.deleteVendorById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}