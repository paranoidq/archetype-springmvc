package me.webapp.exception;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class InitializeException extends AppException {

    public InitializeException() {
    }

    public InitializeException(String message) {
        super(message);
    }

    public InitializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializeException(Throwable cause) {
        super(cause);
    }

    public InitializeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
