package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class InvalidAppointmentException extends Exception {

    final String msg;

	public InvalidAppointmentException() {
		super();
		this.msg = "";
			
	}

	public InvalidAppointmentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public InvalidAppointmentException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public InvalidAppointmentException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public InvalidAppointmentException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}

    

}