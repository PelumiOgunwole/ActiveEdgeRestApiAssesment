package com.pelumi.demo.exception;

public class StockNotExistingException extends  RuntimeException{
    public StockNotExistingException(String message) {
        super(message);
    }
}
