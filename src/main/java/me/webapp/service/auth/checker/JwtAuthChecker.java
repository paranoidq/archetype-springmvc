package me.webapp.service.auth.checker;

import me.webapp.common.util.jwt.JwtUtil;
import me.webapp.service.auth.AuthChecker;
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
