package com.spring.GlobalExceptionHandling.exception.business;

public class BadInputException extends RuntimeException {
    public BadInputException(String message) {
        super(message);
    }
}
