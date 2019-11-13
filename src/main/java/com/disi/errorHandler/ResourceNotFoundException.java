package com.disi.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity was not found in database.")
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String exception){
        super(exception);
    }

}