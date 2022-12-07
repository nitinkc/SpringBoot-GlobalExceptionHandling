package com.spring.GlobalExceptionHandling.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends ApiBusinessException {


	/**
	 * Orders Command domain custom exception for business validations
	 */
	private static final long serialVersionUID = 2652858543957870604L;

	private String[] errorParams;

	public ApiException(Throwable cause) {
		super(cause);
	}
	public ApiException(String statusMessage) {
		super(statusMessage);
	}
	public ApiException(String statusMessage, Exception e) {
		super(statusMessage, e);
	}

	public ApiException(String errorCode, HttpStatus statusCode, String statusMessage, String[] errorParams) {
		super(errorCode, statusCode, ParameterizedMessage.format(statusMessage, errorParams));
	}

	public ApiException(String errorCode, HttpStatus statusCode, String statusMessage, String[] errorParams, final String[] thrownByMethodArgs) {
		super(errorCode, statusCode, ParameterizedMessage.format(statusMessage, errorParams),thrownByMethodArgs);
	}

	public ApiException(HttpStatus statusCode, String statusMessage) {
		super(statusCode, statusMessage);
	}

	public ApiException(HttpStatus statusCode, String statusMessage, Exception e) {
		super(statusCode, statusMessage, e);
	}

	public ApiException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
						final String[] thrownByMethodArgs) {
		super(statusCode,statusMessage, thrownByMethod, thrownByMethodArgs);
	}

	public ApiException(final HttpStatus statusCode, final String statusMessage, final String thrownByMethod,
						final String[] thrownByMethodArgs, final Exception e) {
		super(statusCode, statusMessage, thrownByMethod,thrownByMethodArgs, e);
	}
	public ApiException(String errorCode, HttpStatus statusCode, String statusMessage, String[] errorParams, final String[] thrownByMethodArgs, String errorTitle) {
		super(errorCode, statusCode, ParameterizedMessage.format(statusMessage, errorParams), errorTitle);
	}
}
