package me.webapp.config;

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

import org.springframework.beans.factory.annotation.Value;
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
     * 是否开启访问权限验证
     * 默认为true，开启权限验证
     */
    @Value("${webapp.auth.enabled:false}")
    private boolean authEnabled;

    /**
     * 鉴权模式，默认为{@link me.webapp.support.auth.checker.AlwaysPassChecker}鉴权模式
     */
    @Value("${webapp.auth.checker:me.webapp.support.auth.checker.AlwaysPassChecker}")
    private String authChecker;

    public String getStaticResourceVersion() {
        return staticResourceVersion;
    }

    public String getAuthChecker() {
        return authChecker;
    }
}
