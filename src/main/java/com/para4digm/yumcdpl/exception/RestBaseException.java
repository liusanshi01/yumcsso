package com.para4digm.yumcdpl.exception;

public class RestBaseException extends RuntimeException {
    private String status;

    public RestBaseException(String msg) {
        super(msg);
    }

    public RestBaseException(String status, String msg) {
        super(msg);
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}