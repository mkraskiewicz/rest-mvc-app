package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.mkraskiewicz.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Maciej on 09/07/2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerIT {

    public static final String NAME = "Vendor 1";

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @Before
    public void setUp(){

        vendorDTO1 = new VendorDTO("Vendor 1", VendorController.BASE_URL + "/1");
        vendorDTO2 = new VendorDTO("Vendor 2", VendorController.BASE_URL + "/2");
    }

    @Test
    public void getAllVendors() throws Exception{

        //given
        List<VendorDTO> vendorDTOList = Arrays.asList(vendorDTO1,vendorDTO2);
        when(vendorService.getAllVendors()).thenReturn(vendorDTOList);

        //when/then
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    public void getVendorById() throws Exception{

        //given

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);

        //when/then
        mockMvc.perform(get(VendorController.BASE_URL + "/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }

    @Test
    public void createNewVendor() throws Exception{

        //given
        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(vendorDTO1);

        //when/then
        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));



    }

    @Test
    public void updateVendor() throws Exception{

        //given
        when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO1);

        //when/then
        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));


    }

    @Test
    public void patchVendor() throws Exception{

        //given
        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO1);

        //when/then
        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));

    }

    @Test
    public void deleteVendor() throws Exception{

        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(vendorService, timeout(1)).deleteVendorById(anyLong());

    }
}