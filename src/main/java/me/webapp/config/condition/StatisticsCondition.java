package me.webapp.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Condition条件，满足该条件时，启用status组件功能
 *
 *
 * 可以通过启用-Dwebapp.status.enabled=true来开启该功能
 * 或放入ServletContext启动参数中
 * 或通过app.properties配置
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class StatisticsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String statusEnabled = context.getEnvironment().getProperty("webapp.statics.enabled");
        return statusEnabled != null && statusEnabled.equalsIgnoreCase("true");
    }
}
