package com.g6.onlineeyecare.exceptions;

@SuppressWarnings("serial")
public class ReportIdNotFoundException extends Exception {

    final String msg;

	public ReportIdNotFoundException() {
		super();
		this.msg = "";
		
		
	}

	public ReportIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = "";
		
		
	}

	public ReportIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.msg = "";
		
		
	}

	public ReportIdNotFoundException(String message) {
		super(message);
		this.msg = "";
		
		
	}

	public ReportIdNotFoundException(Throwable cause) {
		super(cause);
		this.msg = "";
		
		
	}

	public String getMsg() {
		return msg;
	}

	

}