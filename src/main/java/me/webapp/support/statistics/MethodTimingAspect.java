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
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 *
 * 记录方法调用耗时
 *
 * 必须同时满足{@link me.webapp.config.condition.StatisticsCondition}才能使得该AOP生效
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
@Conditional(StatisticsCondition.class)
public class MethodTimingAspect {
    private static final Logger logger = LoggerFactory.getLogger(MethodTimingAspect.class);

    /**
     * 拦截所有的标记了ElapsedTimeCounter注解的方法
     */
    @Pointcut("@annotation(me.webapp.support.statistics.EnableMethodTiming)")
    public void methodTimeingPointcut() {}


    @Around("methodTimeingPointcut()")
    public Object logMethodTime(ProceedingJoinPoint joinPoint) {
        Object retVal = null;
        Object[] args = joinPoint.getArgs();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            retVal = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            logger.error("统计某方法执行耗时环绕通知出错", throwable);
        }
        stopWatch.stop();

        long elapsed = stopWatch.getTime();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(LogTag.Statis.METHOD_TIMING + "方法执行耗时("+ className + "#" + methodName + "): " + elapsed + "ms");

        return retVal;
    }

}
