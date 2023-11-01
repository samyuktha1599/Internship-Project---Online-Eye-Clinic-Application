package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class SpectaclesIdNotFoundException extends Exception{

    final String msg;

	public SpectaclesIdNotFoundException() {
		super();
		this.msg = "";
		
		
	}

	public SpectaclesIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public SpectaclesIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public SpectaclesIdNotFoundException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public SpectaclesIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}



}