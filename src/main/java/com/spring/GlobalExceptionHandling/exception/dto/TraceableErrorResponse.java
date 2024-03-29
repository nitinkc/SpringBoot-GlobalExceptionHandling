package com.spring.GlobalExceptionHandling.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * A serializable traceable error for tracking and triaging exception and error flows in platform or framework
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraceableErrorResponse implements Serializable {

	private static final long serialVersionUID = -8292996518147711687L;
	private String from;
	private String path;
	private String timestamp;
	private String errorCode;
	private String errorDescription;
	private String exceptionType;
	private HttpStatus statusCode;
	private String thrownByMethod;
	private String thrownByClass;
	private String[] thrownByMethodArgs;
	private String exceptionMessage;
	private String errorTitle;
}