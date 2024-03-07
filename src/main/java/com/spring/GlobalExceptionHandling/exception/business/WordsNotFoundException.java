package com.spring.GlobalExceptionHandling.exception.business;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordsNotFoundException extends RuntimeException {
    public WordsNotFoundException(String message) {
        super(message);
    }
}