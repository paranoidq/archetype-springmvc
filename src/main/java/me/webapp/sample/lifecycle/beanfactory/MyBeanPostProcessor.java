package me.webapp.sample.lifecycle.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor, Ordered{

    // 应用场景：
    // 返回bean的包装类或代理类
    // 在bean执行自身的初始化函数前对bean进行统一的处理

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调动BeanPostProcessor.postProcessBeforeInitialization()方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调动BeanPostProcessor.postProcessAfterInitialization()方法");
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
