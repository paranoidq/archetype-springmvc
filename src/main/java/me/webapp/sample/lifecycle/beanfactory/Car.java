package me.webapp.sample.lifecycle.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware {

    private String brand;

    private String color;

    public Car() {
        System.out.println("调用Car的构造函数");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("调用setBrand设置属性");
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        System.out.println("调用setColor设置属性");
        this.color = color;
    }

    public void myInit() {
        System.out.println("调用XML init-method的方法初始化car实例");
    }

    public void myDestroy() {
        System.out.println("调用XML destroy-method的方法初始化car实例");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory()");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("调用BeanNameAware.setBeanName()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy()销毁bean实例");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializationBean.afterProperties初始化Bean");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("调用ApplitationContextAware.setApplicationContext方法");
    }
}
