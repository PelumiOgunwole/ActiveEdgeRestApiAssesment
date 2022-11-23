package com.pelumi.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/* @ControllerAdvice annotation helps to catch errors
    or Exceptions in any part of the application and throw back a meaningful error respnse.

    @ExceptionHandler manages specific Exceptions where used

 */
@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler(StockDuplicateException.class)
    public ResponseEntity<Object> handleExceptions(StockDuplicateException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Specified Stock Already Exists");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(StockNotExistingException.class)
    public ResponseEntity<Object> handleMissingBookNameField(StockNotExistingException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Stock not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }


}
