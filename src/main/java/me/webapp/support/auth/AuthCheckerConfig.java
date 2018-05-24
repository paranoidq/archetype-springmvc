package me.webapp.support.auth;

import me.webapp.config.AppConfig;
import me.webapp.exception.AuthException;
import me.webapp.log.LogTag;
import me.webapp.support.auth.checker.AlwaysPassChecker;
import me.webapp.support.auth.checker.AuthChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * AuthChcker配置类
 *
 * 该类根据配置文件的类名构造
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class AuthCheckerConfig {

    private static final Logger logger = LoggerFactory.getLogger(AuthCheckerConfig.class);

    @Autowired
    private AppConfig appConfig;

    @Bean
    public AuthChecker authChecker() {
        String authCheckerClazz = appConfig.getAuthChecker();
        if (StringUtils.isEmpty(authCheckerClazz)) {
            return new AlwaysPassChecker();
        }
        try {
            Class<?> clazz = Class.forName(authCheckerClazz);
            logger.info(LogTag.LOGGING.AUTH + "使用AuthChecker: [{}]", authCheckerClazz);
            return (AuthChecker) clazz.newInstance();
        } catch (Throwable t) {
            throw new AuthException("无法加载AuthChecker类: " + authCheckerClazz, t);
        }
    }
}
