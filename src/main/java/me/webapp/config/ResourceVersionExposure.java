package me.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Date;

/**
 * 配置静态资源版本处理，通过自动添加版本号来管理静态资源的更新
 *
 * TODO 为什么不用ApplicationContextAware ？？？？
 * @author paranoidq
 * @since 1.0.0
 */
public class ResourceVersionExposure implements ServletContextAware {

    private ServletContext servletContext;
    private String resourceVersion;

    @Value("${webapp.static.version}")
    private String version = "1.0";

    public void init() {
        resourceVersion = version;
        getServletContext().setAttribute("resourceVersion", resourceVersion);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }
}
