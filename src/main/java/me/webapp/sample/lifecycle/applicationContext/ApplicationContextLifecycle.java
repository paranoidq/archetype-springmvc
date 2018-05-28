package me.webapp.sample.lifecycle.applicationContext;

import me.webapp.sample.lifecycle.beanfactory.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class ApplicationContextLifecycle {

    private static void lifecycle() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("me/webapp/sample/lifecycle/applicationContext/beans.xml");
        Car car = (Car) applicationContext.getBean("car");
    }

    public static void main(String[] args) {
        lifecycle();
    }

    /*
    output:

        调用BeanFactoryPostProcessor.postProcessBeanFactory()方法
        调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法
        调用Car的构造函数
        调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法
        调用InstantiationAwareBeanPostProcessor.postProcessPropertyValues()方法
        调用setBrand设置属性
        调用setColor设置属性
        调用BeanNameAware.setBeanName()
        调用BeanFactoryAware.setBeanFactory()
        调用ApplitationContextAware.setApplicationContext方法
        调用InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization()方法
        调动BeanPostProcessor.postProcessBeforeInitialization()方法
        调用InitializationBean.afterProperties初始化Bean
        调用XML init-method的方法初始化car实例
        调用InstantiationAwareBeanPostProcessor.postProcessAfterInitialization()方法
        调动BeanPostProcessor.postProcessAfterInitialization()方法
     */
}
