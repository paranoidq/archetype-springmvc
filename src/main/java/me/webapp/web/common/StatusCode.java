package me.webapp.web.common;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public enum StatusCode {
    OK(0),
    AUTH_ERROR(1),
    SERVICE_ERROR(2),

    ;

    private int code;

    StatusCode(int code) {
        this.code = code;
    }
}
