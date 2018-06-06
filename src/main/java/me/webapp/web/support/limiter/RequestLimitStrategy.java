package me.webapp.web.support.limiter;

/**
 *
 * TODO
 *
 * @author paranoidq
 * @since 1.0.0
 */
public interface RequestLimitStrategy {

    boolean decide();
}
