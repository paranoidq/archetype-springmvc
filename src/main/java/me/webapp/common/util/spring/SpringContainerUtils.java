package me.webapp.common.util.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring容器工具类
 *
 *      1. 注入常用的spring容器相关实例：ApplicationContext、ResourceLoader、BeanFactory等
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class SpringContainerUtils {

    /**
     * 注入ApplicationContext实例
     * 另一种方式：实现{@link org.springframework.context.ApplicationContextAware}接口
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 注入ResourceLoader实例
     * 另一种方式：实现{@link org.springframework.context.ResourceLoaderAware}接口
     */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 注入BeanFactory实例
     * 另一种方式：实现
     */
    @Autowired
    private BeanFactory beanFactory;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }


    /**
     * 获取当前请求的ServletRequest对象实例
     * @return
     */
    public HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }




    /**
     * 刷新应用上下文，重新加载配置文件
     */
    public void refreshContext() {
        if (applicationContext instanceof XmlWebApplicationContext) {
            ((XmlWebApplicationContext) applicationContext).refresh();
        }
    }
}
