package me.webapp.sample.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 三种方式构建bean生命周期函数；
 *  1. 注解： {@link PostConstruct}、{@link PreDestroy}
 *  2. xml中bean声明时定义：init-method和destroy-method方法
 *  3. 实现{@link org.springframework.beans.factory.InitializingBean}和{@link org.springframework.beans.factory.DisposableBean}接口
 *
 *  三种方式的顺序：
 *  1. 先执行注解
 *  2. 然后执行接口方法的实现
 *  3. 最后执行xml中声明的init-method和destroy-method
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */

@Component
public class BeanLifetimeHook implements InitializingBean, DisposableBean, BeanNameAware {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifetimeHook.class);

    @PostConstruct
    public void afterCreate() {
        logger.debug("@PostConstruct");
    }


    @PreDestroy
    public void beforeDestroy() {
        logger.debug("@PreDestroy");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("InitializingBean#afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        logger.debug("DisposableBean#destroy");
    }


    @Override
    public void setBeanName(String name) {

    }
}
