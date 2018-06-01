package me.webapp.common.util.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public final class RateLimiterUtil {

    private static final int defaultPermitsPerSecond = 100;
    private static final int defaultWarmupPeriodSeconds = 0;

    private static RateLimiter createDefault() {
        RateLimiter rateLimiter = RateLimiter.create(
            defaultPermitsPerSecond,
            defaultWarmupPeriodSeconds,
            TimeUnit.SECONDS
        );
        return rateLimiter;
    }


    public static RateLimiter create(int permitsPerSecond) {
        RateLimiter rateLimiter = RateLimiter.create(
            permitsPerSecond,
            defaultWarmupPeriodSeconds,
            TimeUnit.SECONDS
        );
        return rateLimiter;
    }

}
