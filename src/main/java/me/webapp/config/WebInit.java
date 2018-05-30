package me.webapp.config;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.*;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Properties;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class WebInit implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /*
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("classpath:config/web.properties");
        } catch (IOException e) {
            System.err.println("找不到web.properties配置，启动web应用失败");
            e.printStackTrace();
            System.exit(1);
        }

        if (properties == null) {
            System.err.println("找不到web.properties配置，启动web应用失败");
            return;
        }
        servletContext.setInitParameter("contextPath", (String) properties.getOrDefault("contextPath", "/springmvc"));
        servletContext.setInitParameter("log4jConfiguration", (String) properties.getOrDefault("log4jConfiguration", "config/log4j2.xml"));

        servletContext.addListener(new IntrospectorCleanupListener());
        servletContext.addListener("org.apache.logging.log4j.web.Log4jServletContextListener");
        servletContext.addListener(new ContextLoaderListener());



        // 新建Spring WebApplicationContext容器，并关联ServletContext
        AnnotationConfigWebApplicationContext applicationContext =
            new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation((String) properties.getOrDefault("contextConfigLocation", "classpath:config/application-all.xml"));
        applicationContext.setServletContext(servletContext);



        FilterRegistration.Dynamic encodingFilterRegistration = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilterRegistration.setInitParameter("encoding", (String) properties.getOrDefault("filterEncoding", "UTF-8"));
        encodingFilterRegistration.addMappingForUrlPatterns(
            EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), true, "/*"
        );


        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("spring-dispatcher", new DispatcherServlet(applicationContext));
        servletRegistration.setInitParameter("contextConfiguration",
            (String) properties.getOrDefault("contextConfiguration", "spring-dispathcer-servlet.xml"));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.setAsyncSupported(true);
        servletRegistration.addMapping("/*");
         */

    }

}
