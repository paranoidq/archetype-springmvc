package me.webapp.web.support.limiter.limiters;

import me.webapp.web.support.limiter.RequestLimitInfo;
import me.webapp.web.support.limiter.RequestLimiter;

/**
 * 拒绝所有流量
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class ForbiddenLimiter implements RequestLimiter {

    @Override
    public boolean decide(RequestLimitInfo requestInfo) {
        return false;
    }
}
