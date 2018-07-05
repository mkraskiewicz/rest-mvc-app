package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.CustomerDTO;
import com.mkraskiewicz.services.CustomerService;
import org.json.JSONString;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.mkraskiewicz.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Maciej on 04/07/2018
 */
public class CustomerControllerTest {


    public static final String FIRST_NAME = "Maciej";
    public static final String LAST_NAME = "Kraskiewicz";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void ListCustomersTest() throws Exception{

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(LAST_NAME);
        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO,customerDTO1);
        when(customerService.getAllCustomers()).thenReturn(customerDTOList);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));
    }

    @Test
    public void getCategoryByNameTest() throws Exception{

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)));
    }

    @Test
    public void createNewCustomerTest() throws Exception{

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.crateNewCustomer(customerDTO)).thenReturn(returnDTO);


        //when/then
        mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer_url",equalTo("/api/v1/customers/1")));
    }

    @Test
    public void updateCustomerTest() throws Exception{

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then

        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer_url",equalTo("/api/v1/customers/1")));
    }
}