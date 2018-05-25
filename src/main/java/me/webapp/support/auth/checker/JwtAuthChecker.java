package me.webapp.support.auth.checker;

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

import me.webapp.common.util.jwt.JwtUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 以JWT的方式验证权限
 *
 * 从request parameter或cookie中检查jwt token是否存在，如果不存在，则验证失败
 * 如果存在，进一步检查jwt是否合法，如果不合法，则验证失败；否则验证成功
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class JwtAuthChecker implements AuthChecker {

    public static final String AUTH_TOKEN_KEY = "auth_token";

    /**
     *
     * @param request
     * @return
     */
    @Override
    public boolean check(HttpServletRequest request) {
        String authToken = null;

        // 1. check request parameters if exists auth_token
        authToken = request.getParameter(AUTH_TOKEN_KEY);
        if (StringUtils.isEmpty(authToken)) {
            // 2. check cookie if exists auth_token
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(AUTH_TOKEN_KEY)) {
                    authToken = cookie.getValue();
                }
            }
        }

        if (StringUtils.isEmpty(authToken)) {
            return false;
        }
        return JwtUtil.getInstance().validate(authToken);
    }
}
