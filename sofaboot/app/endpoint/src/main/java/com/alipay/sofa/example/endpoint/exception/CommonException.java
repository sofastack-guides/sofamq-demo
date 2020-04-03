package com.alipay.sofa.example.endpoint.exception;

public class CommonException extends Exception{

    /***
     * You should define you errorCode here, this is only an example
     */
    private String errorCode = "CommonError";

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
