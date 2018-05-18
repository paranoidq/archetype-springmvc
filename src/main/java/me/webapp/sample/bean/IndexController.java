package me.webapp.sample.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class IndexController implements FactoryBean<IndexController.Car> {

    @Override
    public Car getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


    public static class Car {

        public Car() {
        }
    }
}
