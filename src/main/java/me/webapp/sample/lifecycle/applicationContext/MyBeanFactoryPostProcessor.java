package me.webapp.sample.lifecycle.applicationContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("car");

        // xml中不定义这个属性，通过工厂后处理器增加，日志中依然会出现setter函数调用，证明成功修改了beanDefinition
        beanDefinition.getPropertyValues().addPropertyValue("brand", "qq");

        System.out.println("调用BeanFactoryPostProcessor.postProcessBeanFactory()方法");
    }
}
