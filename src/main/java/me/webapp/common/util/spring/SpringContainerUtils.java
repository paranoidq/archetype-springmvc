package me.webapp.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Spring容器工具类
 *
 *      1. 注入常用的spring容器相关实例：ApplicationContext、ResourceLoader、BeanFactory等
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class SpringContainerUtils {

    /**
     * 注入ApplicationContext实例
     * 另一种方式：实现{@link org.springframework.context.ApplicationContextAware}接口
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 注入ResourceLoader实例
     * 另一种方式：实现{@link org.springframework.context.ResourceLoaderAware}接口
     */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 注入BeanFactory实例
     * 另一种方式：实现
     */
    @Autowired
    private BeanFactory beanFactory;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
