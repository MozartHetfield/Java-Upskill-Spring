package com.javaupskill.springdemo.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseException extends Exception {
    HttpStatus status;

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
