package me.webapp.service.auth;

import me.webapp.common.util.spring.SpringContainerUtils;
import me.webapp.config.condition.AuthenticateCondition;
import me.webapp.exception.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * 鉴权切面
 *
 * 该切面往Controller中织入权限校验的逻辑，并在调用Controller相应的handler处理请求之前进行权限的验证
 * 权限验证采用代理模式，代理给{@link AuthChecker}的实现类
 *
 * 只有在满足{@link AuthenticateCondition}的条件下，才会开启该AOP织入
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Service
@Conditional(AuthenticateCondition.class)
public class ControllerAuthCheckService {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAuthCheckService.class);

    @Autowired
    SpringContainerUtils containerUtils;

    @Autowired
    private AuthChecker authChecker;


    /**
     * 拦截注解了{@link org.springframework.web.bind.annotation.RequestMapping}和{@link AuthCheck}的方法调动
     */
    @Pointcut("@annotation(me.webapp.service.auth.AuthCheck) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void authPointcut() {
    }


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
            logger.info("权限验证通过，允许访问");
            return joinPoint.proceed(joinPoint.getArgs());
        }
        throw new AuthException("权限验证失败，拒绝访问");
    }

}
