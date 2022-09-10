package com.DarkBlog.error;

import java.util.function.Supplier;

public class DoesNotExistException extends Exception  {
    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(String message) {
        super(message);
    }

    public DoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected DoesNotExistException(String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
