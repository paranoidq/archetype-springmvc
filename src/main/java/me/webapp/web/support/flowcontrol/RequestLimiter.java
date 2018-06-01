package me.webapp.web.support.flowcontrol;

/**
 *
 * 限流器抽象接口
 * 提供高度的可扩展性，用户可以根据需求设计不同的限流器实现
 *
 * 例如：请求用户较少时，可以采用{@link me.webapp.web.support.flowcontrol.limiters.GuavaLimiter}
 * 而有大量独立的请求用户时，由于GuavaLimiter限流信息存储在JVM内存的Map中，势必难以负载，此时就可以考虑
 * 采用{@link me.webapp.web.support.flowcontrol.limiters.RedisLimiter}或其他自行实现的基于外部缓存的限流器
 *
 *
 * TODO: 可定制限流策略
 *
 * @author paranoidq
 * @since 1.0.0
 */
public interface RequestLimiter {

    boolean decide(RequestLimitInfo requestInfo);

}
