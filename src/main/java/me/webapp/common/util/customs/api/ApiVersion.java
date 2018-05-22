package me.webapp.common.util.customs.api;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 标识api的版本号
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * 版本号，默认值为1.0
     *
     * @return 版本号
     */
    double value() default 1.0;
}
