package me.webapp.sample.hooks;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 针对所有的beans统一处理的生命周期钩子
 *
 * - {@link BeanPostProcessor}
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class BeansLifetimeHook implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
