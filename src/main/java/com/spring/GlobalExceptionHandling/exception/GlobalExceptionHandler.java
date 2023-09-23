package com.spring.GlobalExceptionHandling.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.transaction.IllegalTransactionStateException;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Date;

@ControllerAdvice
@Slf4j
@Getter
@Setter
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { WordsNotFoundException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public @ResponseBody TraceableError handleApiException(WordsNotFoundException ex, HttpServletRequest request){

        TraceableError error = TraceableError.builder()
                .errorCode(ex.getErrorCode())
                .errorDescription(ex.getStatusMessage())
                .exceptionType(ex.getClass().getSimpleName())
                .exceptionMessage(ExceptionUtils.getStackTrace(ex))
                .path(request.getRequestURI())
                .statusCode(ex.getStatusCode())
                .thrownByMethodArgs(ex.getThrownByMethodArgs())
                .thrownByMethod(ex.getStackTrace()[0].getMethodName())
                .thrownByClass(ex.getStackTrace()[0].getClassName())
                .timeStamp(new Date())
                .errorTitle(ex.getErrorTitle() != null ? ex.getErrorTitle() :"Error" )
                .build();

        return error;
    }


    @ExceptionHandler(value = { RestClientResponseException.class })
    public ResponseEntity<Object> handleRestClinetException(  RestClientResponseException ex, WebRequest request){
        log.error("Exception Occur For RestCall Clinet Exception. ResponseBody is: {}", ex.getResponseBodyAsString());
        log.error("handle Rest Clinet Exception. Handling Exception... Caught exception: {}", ExceptionUtils.getStackTrace(ex));

        HttpStatus httpStatus = Arrays.stream(HttpStatus.values())
                .filter(status -> status.name().equals(HttpStatus.valueOf(ex.getRawStatusCode()).getReasonPhrase()))
                .findAny()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        TraceableError error = TraceableError.builder()
              //  .path(request.getContextPath())
                .timeStamp(new Date())
                .errorCode(String.valueOf(ex.getRawStatusCode()))
                .errorDescription(ex.getResponseBodyAsString())
                .exceptionType(ex.getClass().getSimpleName())
                .exceptionMessage(ExceptionUtils.getStackTrace(ex))
                .statusCode(httpStatus)
                .thrownByMethod(ex.getStackTrace()[0].getMethodName())
                .thrownByClass(ex.getStackTrace()[0].getClassName())
                .build();

        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getRawStatusCode()));


    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        log.error("handle Exception. Handling Exception... Caught exception: {}", ExceptionUtils.getStackTrace(ex));
        TraceableError error = TraceableError.builder()
                .timeStamp(new Date())
                .errorDescription(Arrays.toString(ex.getStackTrace()))
                .exceptionType(ex.getClass().getSimpleName())
                .exceptionMessage(ExceptionUtils.getStackTrace(ex))
                .statusCode(ex instanceof IllegalTransactionStateException ? HttpStatus.CONFLICT :  HttpStatus.INTERNAL_SERVER_ERROR)
                .thrownByMethod(ex.getStackTrace()[0].getMethodName())
                .thrownByClass(ex.getStackTrace()[0].getClassName())
                .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatusCode());
    }
}
