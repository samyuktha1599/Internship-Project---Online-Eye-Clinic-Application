package com.g6.onlineeyecare.exceptions;

public class MyExceptionResponse {

    private int httpStatus;
    private String exceptionMsg;

    public MyExceptionResponse() {
        super();
        
    }
    public MyExceptionResponse(int httpStatus, String exceptionMsg) {
        super();
        this.httpStatus = httpStatus;
        this.exceptionMsg = exceptionMsg;
    }
    public int getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getExceptionMsg() {
        return exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

}
