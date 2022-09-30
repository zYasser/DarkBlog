package com.DarkBlog.error;

public class UnauthenticatedException extends Exception {
    public UnauthenticatedException() {
        super();
    }

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedException(Throwable cause) {
        super(cause);
    }

    protected UnauthenticatedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
