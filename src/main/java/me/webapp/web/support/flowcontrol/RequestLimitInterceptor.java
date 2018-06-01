package me.webapp.web.support.flowcontrol;

import me.webapp.web.support.flowcontrol.limiters.UnrestrainedLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求限流拦截器，具体的丽娜姐逻辑代理给{@link RequestLimiter#decide(RequestLimitInfo)}
 * 应用根据需求配置具体的实现类
 *
 * 例如：请求用户较少时，可以采用{@link me.webapp.web.support.flowcontrol.limiters.GuavaLimiter}
 * 而有大量独立的请求用户时，由于GuavaLimiter限流信息存储在JVM内存的Map中，势必难以负载，此时就可以考虑
 * 采用{@link me.webapp.web.support.flowcontrol.limiters.RedisLimiter}或其他自行实现的基于外部缓存的限流器
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class RequestLimitInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(RequestLimitInterceptor.class);

    private RequestLimiter limiter = new UnrestrainedLimiter();


    public RequestLimitInterceptor() {
    }

    public RequestLimitInterceptor(RequestLimiter limiter) {
        this.limiter = limiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (limiter.decide(RequestLimitInfo.create(request))) {
            logger.info("[流量控制] 允许访问");
            return true;
        }
        logger.info("[流量控制] 拒绝访问");
        response.getOutputStream().write("[流量控制] 拒绝访问".getBytes());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
