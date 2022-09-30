package com.DarkBlog.error;

public class PasswordMatchException extends Exception{
    public PasswordMatchException() {
        super();
    }

    public PasswordMatchException(String message) {
        super(message);
    }

    public PasswordMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordMatchException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
