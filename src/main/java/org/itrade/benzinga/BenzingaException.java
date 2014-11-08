package org.itrade.benzinga;

/**
 * Created by dimapod on 07/11/14.
 */
public class BenzingaException extends RuntimeException {
    public BenzingaException() {
        super();
    }

    public BenzingaException(String message) {
        super(message);
    }

    public BenzingaException(String message, Throwable cause) {
        super(message, cause);
    }

    public BenzingaException(Throwable cause) {
        super(cause);
    }

    protected BenzingaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
