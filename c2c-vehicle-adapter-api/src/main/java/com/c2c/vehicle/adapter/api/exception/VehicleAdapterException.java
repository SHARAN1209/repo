package com.c2c.vehicle.adapter.api.exception;

public class VehicleAdapterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5075196374811798986L;
	
	 private final String errorCode;
	    private final String errorMessage;

	    public VehicleAdapterException(String errorCode, String message) {
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
