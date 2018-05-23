package me.webapp.service.auth.checker;

import me.webapp.service.auth.AuthChecker;

import javax.servlet.http.HttpServletRequest;

/**
 * 总是不通过权限验证，即不允许任何访问
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class AlwaysFailChecker implements AuthChecker {

    @Override
    public boolean check(HttpServletRequest request) {
        return false;
    }
}
