package me.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/statics.properties")
public class StaticsConfig {

    @Value("${webapp.statics.enabled}")
    private String staticsEnabled = "false";


    public boolean isStaticsEnabled() {
        return staticsEnabled.equals("true");
    }
}
