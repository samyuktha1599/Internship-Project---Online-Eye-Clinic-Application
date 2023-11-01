package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class TestIdNotFoundException extends Exception {
    final String msg;

	public TestIdNotFoundException() {
		super();
		this.msg = "";
		
		
	}

	public TestIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public TestIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
	
	}

	public TestIdNotFoundException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public TestIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}


}
