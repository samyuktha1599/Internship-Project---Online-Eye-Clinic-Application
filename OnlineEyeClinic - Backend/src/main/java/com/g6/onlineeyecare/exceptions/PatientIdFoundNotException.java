package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class PatientIdFoundNotException extends Exception {

    final String msg;

	public PatientIdFoundNotException() {
		super();
		this.msg = "";
		
		
	}

	public PatientIdFoundNotException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public PatientIdFoundNotException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public PatientIdFoundNotException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public PatientIdFoundNotException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}

  


}
