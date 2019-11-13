package com.disi.dto;

public class RestErrorDTO {

    private int status;
    private String message;
    private Object exception;

    public RestErrorDTO(int status, String message, Object exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }
}