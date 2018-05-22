package me.webapp.config;

import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@PropertySource("app.properties")
public class AppConfig {

    public static int JWT_EXPIRE_DURATION_AS_DAY = 1;
}
