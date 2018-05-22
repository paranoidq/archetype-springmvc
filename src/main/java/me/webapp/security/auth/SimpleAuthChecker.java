package me.webapp.security.auth;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class SimpleAuthChecker implements AuthChecker{

    @Override
    public boolean check(HttpServletRequest request) {
        return false;
    }
}
