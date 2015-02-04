package org.itrade.nasdaq;

public class NasdaqException extends RuntimeException {

    public NasdaqException() {
        super();
    }

    public NasdaqException(String message) {
        super(message);
    }

    public NasdaqException(String message, Throwable cause) {
        super(message, cause);
    }

    public NasdaqException(Throwable cause) {
        super(cause);
    }

    protected NasdaqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
