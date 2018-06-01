package me.webapp.web.support.flowcontrol.limiters;

import me.webapp.web.support.flowcontrol.RequestLimitInfo;
import me.webapp.web.support.flowcontrol.RequestLimiter;

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
