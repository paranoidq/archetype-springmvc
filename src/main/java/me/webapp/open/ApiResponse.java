package me.webapp.open;

import me.webapp.exception.ErrorCode;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ApiResponse {

    /**
     * 状态码
     */
    private ErrorCode code;
    /**
     * 报错原因，当状态码为{@link ErrorCode#OK}时，该字段为空
     */
    private String reason;
    /**
     * 返回报文信息
     */
    private Object msg;
    /**
     * 返回应答的时间戳
     */
    private long timestamp;

    // TODO. other useful fields ???


    private ApiResponse(ErrorCode code, String reason, Object msg) {
        this.code = code;
        this.reason = reason;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功时的应答对象实例
     *
     * @param msg
     * @return
     */
    public static ApiResponse createOk(Object msg) {
        return new ApiResponse(ErrorCode.OK, "", msg);
    }

    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @param reason
     * @return
     */
    public static ApiResponse createError(ErrorCode errorCode, String reason) {
        return createError(errorCode, reason, "");
    }

    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @param reason
     * @param message
     * @return
     */
    public static ApiResponse createError(ErrorCode errorCode, String reason, Object message) {
        return new ApiResponse(errorCode, reason, message);
    }




}
