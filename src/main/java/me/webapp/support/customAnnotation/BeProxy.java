package me.webapp.support.customAnnotation;

import java.lang.annotation.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface BeProxy {
}
