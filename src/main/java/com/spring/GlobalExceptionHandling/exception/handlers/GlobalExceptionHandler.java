package com.spring.GlobalExceptionHandling.exception.handlers;

import com.spring.GlobalExceptionHandling.exception.ErrorCodes;
import com.spring.GlobalExceptionHandling.exception.business.BadInputException;
import com.spring.GlobalExceptionHandling.exception.dto.MyExceptionResponse;
import com.spring.GlobalExceptionHandling.exception.dto.TraceableErrorResponse;
import com.spring.GlobalExceptionHandling.exception.business.WordsNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Handling 2 exception classes. Notice the parameter of handleNotFoundExceptions method (BusinessException exception)
    @ExceptionHandler(value = {NumberFormatException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MyExceptionResponse> handleNotFoundExceptions(Exception exception, final HttpServletRequest request) {
        MyExceptionResponse error = MyExceptionResponse.builder()
                .from("From Exception Response")
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .exceptionType(exception.getClass().getSimpleName())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_140.getErrorCode() + " :: " + ErrorCodes.ERR_140.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Handling 2 exception classes. Notice the parameter of handleNotFoundExceptions method (BusinessException exception)
    @ExceptionHandler(value = {IllegalArgumentException.class, BadInputException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MyExceptionResponse> handleInputExceptions(Exception exception, final HttpServletRequest request) {
        MyExceptionResponse error = MyExceptionResponse.builder()
                .from("From Exception Response :: handleInputExceptions")
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .exceptionType(exception.getClass().getSimpleName())
                .methodName(request.getMethod())
                .errorCode(ErrorCodes.ERR_140.getErrorCode() + " :: " + ErrorCodes.ERR_140.getErrorMessage())
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {WordsNotFoundException.class})
    @ResponseStatus(value = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    public @ResponseBody TraceableErrorResponse handleDbExceptions(
            Exception exception, final HttpServletRequest request) {
        return TraceableErrorResponse.builder()
                .from("From Traceable Error")
                .exceptionType(exception.getClass().getSimpleName())
                .exceptionMessage(exception.getMessage())
                .errorTitle(ErrorCodes.ERR_124.getCode())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")))
                .errorDescription(ErrorCodes.ERR_123.getErrorMessage())
                .errorCode(ErrorCodes.ERR_123.getErrorCode())
                //.errorCode(ErrorCodes.ERR_124.getErrorCode())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                .thrownByMethod(exception.getStackTrace()[0].getMethodName())
                .thrownByClass(exception.getStackTrace()[0].getClassName())
                .build();
    }
}