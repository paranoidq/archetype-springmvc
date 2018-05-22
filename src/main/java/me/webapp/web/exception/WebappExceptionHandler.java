package me.webapp.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

/**
 *
 * 三种方式：
 *      1. 直接使用{@link org.springframework.cache.jcache.interceptor.SimpleExceptionCacheResolver}
 *      2. 实现{@link HandlerExceptionResolver}
 *      3. 使用注解：{@link org.springframework.web.bind.annotation.ControllerAdvice} + {@link org.springframework.web.bind.annotation.ExceptionHandler}
 *         注意单独使用ExceptionHandle必须与异常方法在同一个类中
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ControllerAdvice
public class WebappExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebappExceptionHandler.class);


    @ExceptionHandler(Throwable.class)
    public ModelAndView throwable(Throwable t) {
        logger.error("Caught Exception: " + t);
        return new ModelAndView("pages/error");
    }
}
