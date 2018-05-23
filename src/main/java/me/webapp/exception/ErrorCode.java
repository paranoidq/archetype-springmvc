package me.webapp.exception;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public enum ErrorCode {

    OK(0000),

    AUTH_ERROR(1001),


    UPLOAD_ERROR(8001),


    SERVER_UNKNOWN_ERROR(9001)
    ;


    private int code;

    ErrorCode(int code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return Integer.toString(this.code);
    }

}
