package me.webapp.support.auth;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

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
