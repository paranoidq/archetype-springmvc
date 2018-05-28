package me.webapp.sample.lifecycle.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class BeanFactoryLifecycle {

    private static void lifecycle() {
        Resource res = new ClassPathResource("me/webapp/sample/lifecycle/beanfactory/beans.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
        reader.loadBeanDefinitions(res);

        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());


        Car car = (Car) beanFactory.getBean("car");

        ((DefaultListableBeanFactory) beanFactory).destroySingletons();
    }

    /*
    output:

        调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法
        调用Car的构造函数
        调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法
        调用InstantiationAwareBeanPostProcessor.postProcessPropertyValues()方法
        调用setBrand设置属性
        调用setColor设置属性
        调用BeanNameAware.setBeanName()
        调用BeanFactoryAware.setBeanFactory()
        调用InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization()方法
        调动BeanPostProcessor.postProcessBeforeInitialization()方法
        调用InitializationBean.afterProperties初始化Bean
        调用XML init-method的方法初始化car实例
        调用InstantiationAwareBeanPostProcessor.postProcessAfterInitialization()方法
        调动BeanPostProcessor.postProcessAfterInitialization()方法
        调用DisposableBean.destroy()销毁bean实例
        调用XML destroy-method的方法初始化car实例


     其中调用InstantiationAwareBeanPostProcessor和BeanPostProcessor的postProcessBeforeInitialization()和postProcessAfterInitialization的顺序
     取决于添加到内部存储BeanPostProcessor的list中的先后顺序，也即调换上面代码中的addBeanPostProcessor就可以控制顺序

     一般只要一个就可以了
     */


    public static void main(String[] args) {
        lifecycle();
    }
}


