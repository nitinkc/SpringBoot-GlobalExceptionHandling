package com.spring.GlobalExceptionHandling.exception;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnglishWordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnglishWord {
    String message() default "Word must be a valid English word";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

