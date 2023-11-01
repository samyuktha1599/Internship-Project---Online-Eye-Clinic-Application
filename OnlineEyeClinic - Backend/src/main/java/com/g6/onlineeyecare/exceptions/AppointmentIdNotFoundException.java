package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class AppointmentIdNotFoundException extends Exception {
	
	 final String msg;

	public AppointmentIdNotFoundException() {
		super();
		this.msg = "";
		
	}

	public AppointmentIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
	}

	public AppointmentIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
	}

	public AppointmentIdNotFoundException(String message) {
		super(message);
		this.msg = "";
	}

	public AppointmentIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		
	}

	public String getMsg() {
		return msg;
	}
	
}