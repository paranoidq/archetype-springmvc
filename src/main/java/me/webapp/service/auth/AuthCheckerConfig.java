package me.webapp.service.auth;

import me.webapp.config.AppConfig;
import me.webapp.service.auth.checker.AlwaysPassChecker;
import me.webapp.service.auth.checker.JwtChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class AuthCheckerConfig {

    @Autowired
    private AppConfig appConfig;


    @Bean
    public AuthChecker authChecker() {
        String selected = appConfig.getAuthChecker();
        if (StringUtils.isEmpty(selected)) {
            return new AlwaysPassChecker();
        }



    }
}
