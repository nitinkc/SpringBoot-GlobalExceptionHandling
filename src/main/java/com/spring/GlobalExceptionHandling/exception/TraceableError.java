package com.spring.GlobalExceptionHandling.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * A serializable traceable error for tracking and triaging exception and error flows in platform or framework
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraceableError implements Serializable {

	private static final long serialVersionUID = -8292996518147711687L;
	private String path;
	private Date timeStamp;
	private String correlationId;
	private Map<String, String> auditHeaders;
	private Map<String, String> payloadHeaders;
	private String errorCode;
	private String errorDescription;
	private String exceptionType;
	private HttpStatus statusCode;
	private String thrownByMethod;
	private String thrownByClass;
	private String[] thrownByMethodArgs;
	private String exceptionMessage;
	private String errorTitle;

	/**
	 * Default constructor to be used when encapsulating it to an Async/Sync Exception
	 * 
	 * @param correlationId
	 * @param auditHeaders
	 * @param errorCode
	 * @param errorDescription
	 */
	public TraceableError(String correlationId, Map<String, String> auditHeaders, String errorCode, String errorDescription) {
		super();
		this.correlationId = correlationId;
		this.auditHeaders = auditHeaders;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	
	/** 
	 * Use this constructor if adding a List<TraceableException> to an Sync/Async exception
	 * 
	 * @param correlationId
	 * @param auditHeaders
	 * @param errorCode
	 * @param errorDescription
	 * @param exceptionType
	 * @param exceptionMessage
	 */
	public TraceableError(String correlationId, Map<String, String> auditHeaders, String errorCode, String errorDescription, String exceptionType, String exceptionMessage) {
		super();
		this.correlationId = correlationId;
		this.auditHeaders = auditHeaders;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.exceptionType = exceptionType;
		this.exceptionMessage = exceptionMessage;
	}
	
	public TraceableError(String correlationId, Map<String, String> auditHeaders, Map<String, String> payloadHeaders, String errorCode, String errorDescription, String exceptionType, String exceptionMessage) {
		super();
		this.correlationId = correlationId;
		this.auditHeaders = auditHeaders;
		this.payloadHeaders = payloadHeaders;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.exceptionType = exceptionType;
		this.exceptionMessage = exceptionMessage;
	}
}