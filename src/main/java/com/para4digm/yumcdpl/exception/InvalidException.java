package com.para4digm.yumcdpl.exception;

public class InvalidException extends RestBaseException {
    public InvalidException(String msg) {
        super(msg);
    }

    public InvalidException(String status, String msg) {
        super(status, msg);
    }
}
