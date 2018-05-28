package me.webapp.sample.lifecycle.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.beans.PropertyDescriptor;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements Ordered {


    /**
     * 应用场景：可以通过返回bean的代理对象来对bean进行包装，后续只会调用
     *
     * 返回null，采用默认的bean实例化
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调用InstantiationAwareBeanPostProcessor.postProcessPropertyValues()方法");
        }
        return pvs;
    }



    // InstantiationAwareBeanPostProcessor也是BeanPostProcessor，因此下面两个方法是从BeanPostProcessor继承而来的
    // 具体那一个类的方法先被调用，取决于添加到内部一个BeanPostProcessor的顺序
    // 内部都是统一加到BeanPostProcessor，在执行InstantiationAwareBeanPostProcessor时是采用循环遍历的方式，直接从list中找出来执行的

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调用InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization()方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("调用InstantiationAwareBeanPostProcessor.postProcessAfterInitialization()方法");
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
