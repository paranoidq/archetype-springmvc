package me.webapp.security.auth;

import me.webapp.common.util.spring.SpringContainerUtils;
import me.webapp.exception.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 鉴权切面
 * <p>
 * 该切面往Controller中织入权限校验的逻辑
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
public class ControllerAuthAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAuthAspect.class);

    @Autowired
    SpringContainerUtils containerUtils;

    @Autowired
    private AuthChecker authChecker;

    /**
     * 拦截注解了{@link org.springframework.web.bind.annotation.RequestMapping}和{@link CheckAuth}的方法调动
     */
    @Pointcut("@annotation(me.webapp.security.auth.CheckAuth) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void authPointcut() {}


    /**
     * 织入鉴权逻辑
     *
     * @param joinPoint
     * @return
     */
    @Around("authPointcut()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean passed = authChecker.check(containerUtils.getServletRequest());
        if (passed) {
            logger.info("验证通过，允许访问");
            return joinPoint.proceed(joinPoint.getArgs());
        }
        throw new AuthException("权限验证失败");
    }

}
