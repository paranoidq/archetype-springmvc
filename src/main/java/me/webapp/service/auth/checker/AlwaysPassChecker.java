package me.webapp.service.auth.checker;

import me.webapp.service.auth.AuthChecker;

import javax.servlet.http.HttpServletRequest;

/**
 * 总是通过，即不验证请求的权限
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class AlwaysPassChecker implements AuthChecker {

    @Override
    public boolean check(HttpServletRequest request) {
        return true;
    }
}
