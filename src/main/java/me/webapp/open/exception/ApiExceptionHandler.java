package me.webapp.open.exception;

import me.webapp.exception.ErrorCode;
import me.webapp.open.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 *
 * 三种方式：
 *      1. 直接使用{@link org.springframework.cache.jcache.interceptor.SimpleExceptionCacheResolver}
 *      2. 实现{@link HandlerExceptionResolver}
 *      3. 使用注解：{@link ControllerAdvice} + {@link ExceptionHandler}
 *         注意单独使用ExceptionHandle必须与异常方法在同一个类中
 *
 * TODO: i18n支持的errorMessage
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * 通用异常处理接口
     *
     * 处理其他handler没有捕获到的异常，并渲染错误页面
     *
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse throwable(Throwable t) {
        logger.error("未知异常", t);
        return ApiResponse.createError(ErrorCode.SERVER_UNKNOWN_ERROR, "服务器发生了错误");
    }


}
