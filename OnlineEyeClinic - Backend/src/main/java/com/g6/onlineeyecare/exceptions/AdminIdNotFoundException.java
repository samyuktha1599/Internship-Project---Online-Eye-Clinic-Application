package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class AdminIdNotFoundException extends Exception {

    final String msg;

	public AdminIdNotFoundException() {
		super();
		this.msg = "";
		
		
	}

	public AdminIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public AdminIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public AdminIdNotFoundException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public AdminIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}
    
    


}
