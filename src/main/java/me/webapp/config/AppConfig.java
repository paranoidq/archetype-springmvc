package me.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/app.properties")
public class AppConfig {

    public static int JWT_EXPIRE_DURATION_AS_DAY = 1;

    /**
     * 静态资源版本号，默认为1.0
     */
    @Value("${webapp.static.version:1.0}")
    private String staticResourceVersion;


    /**
     * 鉴权模式，默认为{@link me.webapp.service.auth.checker.AlwaysPassChecker}鉴权模式
     */
    @Value("${webapp.auth.checker:me.webapp.service.auth.checker.AlwaysPassChecker}")
    private String authChecker;

    public String getStaticResourceVersion() {
        return staticResourceVersion;
    }

    public String getAuthChecker() {
        return authChecker;
    }
}
