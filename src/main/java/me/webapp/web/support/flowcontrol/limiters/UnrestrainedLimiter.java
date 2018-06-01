package me.webapp.web.support.flowcontrol.limiters;

import me.webapp.web.support.flowcontrol.RequestLimitInfo;
import me.webapp.web.support.flowcontrol.RequestLimiter;
import org.springframework.stereotype.Component;

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
