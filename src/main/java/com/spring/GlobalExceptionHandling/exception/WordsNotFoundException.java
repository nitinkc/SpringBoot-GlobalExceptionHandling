package com.spring.GlobalExceptionHandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordsNotFoundException extends Exception {
    public WordsNotFoundException(String message) {
        super(message);
    }

}
