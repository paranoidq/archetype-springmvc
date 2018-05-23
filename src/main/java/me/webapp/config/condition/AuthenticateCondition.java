package me.webapp.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 *
 * Condition条件，满足该条件时，启用权限验证功能
 *
 * 可以通过启用-Dwebapp.status.enabled=true来开启该功能
 * 或放入ServletContext启动参数中
 * 或通过app.properties配置
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class AuthenticateCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // TODO: 由于加载顺序的问题，如果配置文件没有配置占位符的值，无法取到默认值，这里会是null
        // TODO: 占位符的解析时机是什么时候？？？
        String authEnabled = context.getEnvironment().getProperty("webapp.auth.enabled");
        return authEnabled != null && authEnabled.equalsIgnoreCase("true");
    }
}
