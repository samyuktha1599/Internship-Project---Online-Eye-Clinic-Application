package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialException extends Exception{

	final String msg;

	public InvalidCredentialException() {
		super();
		this.msg = "";
	}

	public InvalidCredentialException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
	}

	public InvalidCredentialException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
	}

	public InvalidCredentialException(String message) {
		super(message);
		this.msg = "";
	}

	public InvalidCredentialException(Throwable cause) {
		super(cause);
		this.msg = "";
	}

	
	
}
