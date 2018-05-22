package me.webapp.status;

import me.webapp.log.LogTag;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service组件状态AOP
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
public class ServiceStatus {

    private static final Logger logger = LoggerFactory.getLogger(ServiceStatus.class);

    /**
     * 拦截service方法调用
     * <p>
     * 被拦截的方法必须满足:
     * - 位于{@link me.webapp.service}包或子包中
     * - 必须是public方法
     */
    @Pointcut("execution(public * me.webapp.service..*.*(..)) ")
    public void serviceMethodPointCut() {
    }


    /**
     * 打印service方法调用日志
     */
    @Before("serviceMethodPointCut()")
    public void serviceMethodInvokeLogging(JoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        logger.info(LogTag.Status.SERVICE + className + "#" + methodName);
    }

}
