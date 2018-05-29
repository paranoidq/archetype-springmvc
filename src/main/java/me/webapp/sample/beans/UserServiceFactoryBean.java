package me.webapp.sample.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class UserServiceFactoryBean implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {
        // 转为DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) bf;

        // 动态注册UserService
        registerUserService(beanFactory);
        // 动态注册UserDao
        registerUserDao(beanFactory);
    }


    private void registerUserDao(DefaultListableBeanFactory beanFactory) {
        // 通过bean的BeanDefinitionBuilder创建bean的定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserDao.class);
        // 注册bean的定义
        beanFactory.registerBeanDefinition("userDao", beanDefinitionBuilder.getRawBeanDefinition());
    }

    private void registerUserService(DefaultListableBeanFactory beanFactory) {
        // 通过bean的BeanDefinitionBuilder创建bean的定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserService.class);

        // 设置属性
        beanDefinitionBuilder.addPropertyReference("userDao", "userDao");

        // 注册bean的定义
        beanFactory.registerBeanDefinition("userService", beanDefinitionBuilder.getRawBeanDefinition());

        // 直接注册bean实例
        // beanFactory.registerSingleton("userService2", new UserService());
    }
}
