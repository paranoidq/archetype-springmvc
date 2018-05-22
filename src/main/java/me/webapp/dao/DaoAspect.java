package me.webapp.dao;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * 织入dao的切面
 * @author paranoidq
 * @since 1.0.0
 */

@Aspect
public class DaoAspect {

    @Before("execution(* me.webapp.dao.*(..))")
    public void beforeDaoInvoke() {

    }


    @After("")
    public void afterDaoInvoke() {

    }
}
