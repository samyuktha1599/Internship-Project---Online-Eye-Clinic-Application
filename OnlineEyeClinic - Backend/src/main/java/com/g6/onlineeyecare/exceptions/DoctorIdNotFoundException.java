package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class DoctorIdNotFoundException extends Exception {
	
	final String msg;

	public DoctorIdNotFoundException() {
		super();
		this.msg = "";
		
	}

	public DoctorIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public DoctorIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public DoctorIdNotFoundException(String message) {
		super(message);
		this.msg = "";
		
	}

	public DoctorIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		

	}

	public String getMsg() {
		return msg;
	}

	
}
