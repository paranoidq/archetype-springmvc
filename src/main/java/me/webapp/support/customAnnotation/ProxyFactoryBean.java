package me.webapp.support.customAnnotation;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 为原始的bean类型创建代理类
 *
 * 这个过程在Spring中很多功能中都需要应用，这里只是举了一个自定义注解扫描的使用案例：
 * 即扫描到的自定义注解的类会被自动创建代理，实际注入到Spring中的是代理类
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class ProxyFactoryBean<T> implements InitializingBean, FactoryBean<T> {
    /**
     * innerClassName作为一个属性，正常情况下定义的bean是没有规定这个属性的
     *
     * 但是自定义注解在扫描的过程中，通过BeanDefinition统一加入了这个属性
     * 也就是从侧面来说，{@link ClassPathBeanDefinitionScanner}也具有改造BeanDefinition的能力
     *
     * @see {@link Scanner#doScan(String...)}
     */
    private String innerClassName;

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    @Override
    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isInterface()) {
            return (T) InterfaceProxy.newInstance(innerClass);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(innerClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setCallback(new InterfaceProxy.MethodInterceptorImpl());
            return (T) enhancer.create();
        }
    }

    @Override
    public Class<?> getObjectType() {
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }



    public static class InterfaceProxy implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("ObjectProxy execute:" + method.getName());
            return method.invoke(proxy, args);
        }

        // JDK dynamic proxy
        public static <T> T newInstance(Class<T> innerInterface) {
            ClassLoader classLoader = innerInterface.getClassLoader();
            Class[] interfaces = new Class[]{innerInterface};
            InterfaceProxy proxy = new InterfaceProxy();
            return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
        }

        // CGLib dynamic proxy
        public static class MethodInterceptorImpl implements MethodInterceptor {

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("MethodInterceptorImpl:" + method.getName());
                return methodProxy.invokeSuper(o, objects);
            }
        }
    }
}

