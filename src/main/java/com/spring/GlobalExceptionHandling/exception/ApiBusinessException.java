package com.spring.GlobalExceptionHandling.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String correlationId;
    private String errorCode;
    private HttpStatus statusCode;
    private String thrownByMethod;
    private String[] thrownByMethodArgs;
    private String statusMessage;
    private String errorTitle;

    public ApiBusinessException(Throwable cause) {
        super(cause);
    }
    public ApiBusinessException(String statusMessage) {
        super(statusMessage);
    }
    public ApiBusinessException(String statusMessage, Exception e) {
        super(statusMessage, e);
    }

    public ApiBusinessException(String statusMessage, String errorCode) {
        super(statusMessage);
        this.errorCode = errorCode;
    }

    public ApiBusinessException(HttpStatus statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage=statusMessage;
    }

    public ApiBusinessException(String errorCode, HttpStatus statusCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.statusMessage=statusMessage;
    }

    public ApiBusinessException(String errorCode, HttpStatus statusCode, String statusMessage, final String[] thrownByMethodArgs) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.thrownByMethodArgs = thrownByMethodArgs;
        this.statusMessage=statusMessage;
    }

    public ApiBusinessException(HttpStatus statusCode, String statusMessage, Exception e) {
        super(statusMessage, e);
        this.statusCode = statusCode;
        this.statusMessage=statusMessage;
    }

    public ApiBusinessException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
                                final String[] thrownByMethodArgs) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.thrownByMethod = thrownByMethod;
        this.thrownByMethodArgs = thrownByMethodArgs;
    }

    public ApiBusinessException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
                                final String[] thrownByMethodArgs, final Exception e) {
        super(statusMessage, e);
        this.statusCode = statusCode;
        this.thrownByMethod = thrownByMethod;
        this.thrownByMethodArgs = thrownByMethodArgs;
    }

    public ApiBusinessException(String errorCode, HttpStatus statusCode, String statusMessage, String errorTitle) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.statusMessage=statusMessage;
        this.errorTitle=errorTitle;
    }

}
