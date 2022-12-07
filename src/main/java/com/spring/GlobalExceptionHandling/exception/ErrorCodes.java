package com.spring.GlobalExceptionHandling.exception;

import org.apache.commons.lang3.StringUtils;

public enum ErrorCodes {
	ERR_122("122", "ERROR: adada;ldad"),
	ERR_123("123", "sdfhjlsafh"),
	ERR_124("124", "Error: ::{}"),
	ERR_140("140", "Error: ::{}"),
	;

	//TODO: Error codes which are dependant on other micro services like Orders service, etc,.

	private String errorCode;
	private String errorMessage;

	public String getCode() {
		return StringUtils.substring(this.name(),0);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	ErrorCodes(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}