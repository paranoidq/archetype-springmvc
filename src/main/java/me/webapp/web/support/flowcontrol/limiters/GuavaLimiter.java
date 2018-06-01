package me.webapp.web.support.flowcontrol.limiters;

import com.google.common.util.concurrent.RateLimiter;
import me.webapp.web.support.flowcontrol.RequestLimitInfo;
import me.webapp.web.support.flowcontrol.RequestLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 基于Guava的限流器
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class GuavaLimiter implements RequestLimiter {

    private static ConcurrentHashMap<RequestLimitInfo, RateLimiter> limiters = new ConcurrentHashMap<>();


    @Override
    public boolean decide(RequestLimitInfo requestInfo) {
        return false;
    }
}
