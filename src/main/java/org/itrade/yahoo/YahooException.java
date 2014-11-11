package org.itrade.yahoo;

/**
 * Created by Dmytro Podyachiy on 11/11/14.
 */
public class YahooException extends RuntimeException{
    public YahooException() {
        super();
    }

    public YahooException(String message) {
        super(message);
    }

    public YahooException(String message, Throwable cause) {
        super(message, cause);
    }

    public YahooException(Throwable cause) {
        super(cause);
    }

    protected YahooException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
