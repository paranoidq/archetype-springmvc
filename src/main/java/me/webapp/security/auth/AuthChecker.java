package me.webapp.security.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权器接口，定义鉴权所需要的基本方法
 *
 * @author paranoidq
 * @since 1.0.0
 */
public interface AuthChecker {

    boolean check(HttpServletRequest request);



}
