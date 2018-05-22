package me.webapp.sample.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 针对所有的beans统一处理的生命周期钩子
 *
 * - {@link BeanPostProcessor}
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class BeansBeforeAndAfterInitializationHook implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeansBeforeAndAfterInitializationHook.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * Output bean information after bean initialized
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Bean [" + beanName + "] created: " + bean.toString() );
        return bean;
    }
}
