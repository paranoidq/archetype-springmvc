package me.webapp.support.statistics;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

import me.webapp.config.condition.StatisticsCondition;
import me.webapp.log.LogTag;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * 打印方法调用日志
 *
 * 必须同时满足{@link me.webapp.config.condition.StatisticsCondition}才能使得该AOP生效
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
@Conditional(StatisticsCondition.class)
public class MethodLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodLoggingAspect.class);

    /**
     * 拦截service方法调用
     *
     * 被拦截的方法必须满足:
     * - 位于{@link me.webapp.service}包或子包中
     * - 必须是public方法
     */
    @Pointcut("@annotation(me.webapp.support.statistics.MethodLogging)")
    public void methodLoggingPointcut() {
    }


    /**
     * 打印service方法调用日志
     */
    @Before("methodLoggingPointcut()")
    public void logServiceMethodInvoke(JoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        logger.info(LogTag.LOGGING.METHOD_INVOKE + "方法调用: " + className + "#" + methodName);
    }

}
