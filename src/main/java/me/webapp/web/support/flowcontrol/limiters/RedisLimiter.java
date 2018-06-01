package me.webapp.web.support.flowcontrol.limiters;

import me.webapp.web.support.flowcontrol.RequestLimitInfo;
import me.webapp.web.support.flowcontrol.RequestLimiter;

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
