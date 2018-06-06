package me.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/open.properties")
public class OpenConfig {


    @Value("${webapp.open.limit.limiterClass:me.webapp.web.support.limiter.limiters.UnrestrainedLimiter}")
    private String limiterClass;

    public String getLimiterClass() {
        return limiterClass;
    }
}
