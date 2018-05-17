package me.webapp.sample.bean;

import me.webapp.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * Bean生命周期相关注解
 *
 * {@link Scope}注解: 作用域，value=singleton或prototype
 *      等价于：xml中配置scope属性
 * {@link Lazy}注解
 *      等价于：xml中配置lazy-init="true"属性
 *
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@Scope("singleton")
@Lazy
public class BeanAnnotations {


    /**
     *
     * @return
     */
    @Bean
    @Lazy
    @Scope("prototype")
    public User getUser() {
        User user = new User();
        return user;
    }
}
