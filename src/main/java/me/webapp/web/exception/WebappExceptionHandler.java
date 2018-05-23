package me.webapp.web.exception;

import me.webapp.exception.AuthException;
import me.webapp.exception.ErrorCode;
import me.webapp.web.common.ViewAttrKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * 三种方式：
 *      1. 直接使用{@link org.springframework.cache.jcache.interceptor.SimpleExceptionCacheResolver}
 *      2. 实现{@link HandlerExceptionResolver}
 *      3. 使用注解：{@link org.springframework.web.bind.annotation.ControllerAdvice} + {@link org.springframework.web.bind.annotation.ExceptionHandler}
 *         注意单独使用ExceptionHandle必须与异常方法在同一个类中
 *
 * TODO: i18n支持的errorMessage
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ControllerAdvice
public class WebappExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebappExceptionHandler.class);

    /**
     * 通用异常处理接口
     *
     * 处理其他handler没有捕获到的异常，并渲染错误页面
     *
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ModelAndView throwable(Throwable t) {
        ModelAndView view = renderErrorView(ErrorCode.SERVER_UNKNOWN_ERROR, "服务器发生了异常");
        logger.error("Uncaught exception", t);
        return view;
    }


    /**
     *
     * @param e service层抛出的{@link AuthException}
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public ModelAndView authException(AuthException e) {
        ModelAndView view = renderErrorView(ErrorCode.AUTH_ERROR, "权限验证失败");
        logger.error("Authentication failed", e);
        return view;
    }


    /**
     * 创建ModeAndView，并设置响应的error属性
     *
     * 该函数会设置http应答码为500
     *
     * @param errorCode 错误代码
     * @param errorMessage 错误提示信息
     * @return
     */
    private ModelAndView renderErrorView(ErrorCode errorCode, String errorMessage) {

        ModelAndView modelAndView = new ModelAndView("pages/error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        modelAndView.getModelMap().addAttribute(ViewAttrKeys.ERROR_CODE, errorCode);
        modelAndView.getModelMap().addAttribute(ViewAttrKeys.ERROR_MESSAGE, errorMessage);

        return modelAndView;
    }




}
