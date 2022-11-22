package com.pelumi.demo.exception;

public class StockDuplicateException extends RuntimeException{

    public StockDuplicateException(String message) {
        super(message);
    }

}
