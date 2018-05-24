package me.webapp.support.auth.checker;

import javax.servlet.http.HttpServletRequest;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public interface AuthChecker {

    /**
     * 根据请求校验权限
     *
     * 子类根据需要实现具体的权限校验逻辑
     *
     *
     * @param request
     * @return true，如果权限校验通过; false，如果权限校验不通过
     */
    boolean check(HttpServletRequest request);
}
