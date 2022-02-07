package com.dee.moneymieassessment.exceptions;

public class ApiException extends Exception {
    private static final String UNKNOWN_ERROR_MSG = "An unknown error has occured";
    protected Object[] args;
    private int code;

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ApiException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        this((Throwable)cause, 0);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public String getMessage() {
        Throwable cause = this.getCause();
            return super.getMessage() != null && !super.getMessage().isEmpty() ? super.getMessage() : null;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public static ApiException getUnknownException() {
        return new ApiException("An unknown error has occured");
    }

    public static ApiException getUnknownException(Throwable t) {
        t.printStackTrace();
        ApiException ex = new ApiException("An unknown error has occured");
        return ex;
    }

    public int getCode() {
        return this.code;
    }
}

