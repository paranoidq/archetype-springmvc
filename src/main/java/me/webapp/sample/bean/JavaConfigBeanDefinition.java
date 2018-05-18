package me.webapp.sample.bean;

import me.webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
/**
 * 一般将@Bean的定义放在@Configuration中，也可以放在@Component中。
 * 区别在于，放在@Configuration中的@Bean定义可以相互调用，类似于xml中的ref，而@Component中的@Bean定义只是java函数调用，无法被Spring容器代理
 */
public class JavaConfigBeanDefinition {

    /**
     * 通过JavaConfig的方式定义一个bean
     *
     * 等价于xml中的bean定义
     *
     * @return
     */
    @Bean(autowire = Autowire.BY_TYPE)
    @Scope("singleton")
    User user() {
        return new User();
    }

}
