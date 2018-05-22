package me.webapp.support.statistics;

import java.lang.annotation.*;

/**
 * 标记该注解的方法会由SpringAOP织入方法调用日志打印的逻辑
 *
 * 必须同时满足{@link me.webapp.config.condition.StatisticsCondition}
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableMethodLogging {
}
