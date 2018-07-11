package com.mkraskiewicz.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Maciej on 04/07/2018
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj){

        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
