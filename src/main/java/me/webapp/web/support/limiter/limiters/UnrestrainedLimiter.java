package me.webapp.web.support.limiter.limiters;

import me.webapp.web.support.limiter.RequestLimitInfo;
import me.webapp.web.support.limiter.RequestLimiter;

/**
 * 无限制
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class UnrestrainedLimiter implements RequestLimiter {

    @Override
    public boolean decide(RequestLimitInfo requestInfo) {
        return true;
    }
}
