package com.mkraskiewicz.exceptions;

/**
 * Created by Maciej on 05/07/2018
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(String message) {

        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause){

        super(message,cause);
    }

    public ResourceNotFoundException(String message, Throwable cause,
                                     boolean enableSuppresion, boolean writableStackTrace) {
        super(message,cause,enableSuppresion,writableStackTrace);
    }

}
