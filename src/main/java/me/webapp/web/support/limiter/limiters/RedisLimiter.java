package me.webapp.web.support.limiter.limiters;

import me.webapp.web.support.limiter.RequestLimitInfo;
import me.webapp.web.support.limiter.RequestLimiter;

/**
 *
 * 基于redis缓存的限流器
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class RedisLimiter implements RequestLimiter {


    @Override
    public boolean decide(RequestLimitInfo requestInfo) {
        return false;
    }
}
