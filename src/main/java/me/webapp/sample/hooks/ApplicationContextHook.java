package me.webapp.sample.hooks;

import me.webapp.web.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现了{@link ApplicationContextAware}的类，可以通过{@link #setApplicationContext(ApplicationContext)}获得ApplicationContext对象
 * 这个函数会在ApplicationContext创建之后被调用
 * 实现ApplicationContextAware的类本身需要注册为一个bean，才能被Spring扫描到
 *
 *
 * 实现了{@link ApplicationContextInitializer}的类，可以对{@link ConfigurableApplicationContext}做一些定制化的工作
 * {@link #initialize}要先于{@link #setApplicationContext}被调用
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class ApplicationContextHook implements ApplicationContextAware, ApplicationContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHook.class);
    private ApplicationContext applicationContext;


    /**
     * Invoked after population of normal bean properties but before an init callback
     * such as org.springframework.beans.factory.InitializingBean.afterPropertiesSet()
     * or a custom init-method. Invoked after ResourceLoaderAware.setResourceLoader,
     * ApplicationEventPublisherAware.setApplicationEventPublisher and MessageSourceAware,
     * if applicable.
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("Class implements ApplicationContextAware can get ApplicationContext reference to do something");

        this.applicationContext = applicationContext;
        // cannot access error ???
//        applicationContext.getBean(BeanLifetimeHook.class)
    }


    /**
     * 没有奏效？？？TODO
     *
     * @param applicationContext
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        logger.debug("initialize applicationContext");
    }
}


