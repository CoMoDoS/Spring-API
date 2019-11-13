package com.disi.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Entity validation failed.")
public class EntityValidationException extends RuntimeException{

    private List<String> errors;

    public EntityValidationException(String msg, List<String> errors) {
        super(msg);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
