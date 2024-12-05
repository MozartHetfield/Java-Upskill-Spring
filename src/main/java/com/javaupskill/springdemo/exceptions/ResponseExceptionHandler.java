package com.javaupskill.springdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseError> handleException(ResponseException exception) {
        ResponseError responseError = generateResponseError(exception);

        responseError.setStatusCode(exception.getStatus().value());
        return new ResponseEntity<>(responseError, exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> handleException(MethodArgumentNotValidException exception) {
        ResponseError responseError = generateResponseError(exception);

        responseError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> handleException(HandlerMethodValidationException exception) {
        ResponseError responseError = generateResponseError(exception);

        responseError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    private static ResponseError generateResponseError(Exception exception) {
        ResponseError responseError = new ResponseError();

        responseError.setMessage(exception.getMessage());
        responseError.setTimestamp(System.currentTimeMillis());

        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();
        responseError.setStackTrace(stackTrace);

        return responseError;
    }
}
