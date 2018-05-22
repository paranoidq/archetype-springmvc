package me.webapp.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 *
 * Condition条件，满足该条件时，启用权限验证功能
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class AuthenticateCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
