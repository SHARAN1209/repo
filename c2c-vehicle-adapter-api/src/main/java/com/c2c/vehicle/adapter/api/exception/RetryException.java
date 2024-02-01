package com.c2c.vehicle.adapter.api.exception;

public class RetryException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 7611449898348575060L;
	private final String errorCode;
	private final String errorMessage;

	public RetryException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
