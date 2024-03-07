package com.spring.GlobalExceptionHandling.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class EnglishWordValidator implements ConstraintValidator<EnglishWord, String> {

    @Override
    public void initialize(EnglishWord constraintAnnotation) {
    }

    @Override
    public boolean isValid(String word, ConstraintValidatorContext constraintValidatorContext) {
        // implement logic here to check if the word is valid.
        // This may involve calling an external service or using an external library.

        return checkIfWordIsValidDictionaryWord(word);

    }

    private boolean checkIfWordIsValidDictionaryWord(String word) {
        // For demonstration purposes, all alphanumeric words are non dictionary words
        return !StringUtils.isAlphanumeric(word);
    }
}
