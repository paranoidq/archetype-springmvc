package me.webapp.sample.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Main {

    private static void invoke() {
        ApplicationContext context = new ClassPathXmlApplicationContext("me/webapp/sample/beans/beans.xml");
        UserService userService = context.getBean(UserService.class);

        // userService实例被创建，并被注入了UserDao依赖
        assert userService.getUserDao() != null;
    }

    public static void main(String[] args) {
        invoke();
    }
}
