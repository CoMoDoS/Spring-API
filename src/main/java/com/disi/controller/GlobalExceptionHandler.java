package com.disi.controller;

import com.disi.dto.RestErrorDTO;
import com.disi.errorHandler.EntityValidationException;
import com.disi.errorHandler.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    @ResponseBody
    public ResponseEntity<RestErrorDTO> handleEntityValidationException(EntityValidationException e) {

        return new ResponseEntity<>(new RestErrorDTO(INTERNAL_SERVER_ERROR.value(), "Wrong data provided when creating user", e), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<RestErrorDTO> handleResourceNotFoundException(ResourceNotFoundException e) {

        return new ResponseEntity<>(new RestErrorDTO(NOT_FOUND.value(), "Resource not found", e), NOT_FOUND);
    }
}
