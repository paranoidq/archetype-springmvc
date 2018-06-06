package me.webapp.config;

import me.webapp.web.support.flowcontrol.RequestLimitInterceptor;
import me.webapp.web.support.flowcontrol.RequestLimiter;
import me.webapp.web.support.flowcontrol.limiters.UnrestrainedLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
//    RequestMappingHandlerAdapter

    @Autowired
    private OpenConfig openConfig;


    /**
     * 限流器bean
     * @return
     */
    @Bean
    public RequestLimitInterceptor flowController(){
        RequestLimiter limiter;
        String clazz = openConfig.getLimiterClass();
        try {
            limiter = (RequestLimiter) Class.forName(clazz).newInstance();
        } catch (Exception e) {
            logger.error("无法加载RequestLimiter实例", e);
            limiter = new UnrestrainedLimiter();
        }
        return new RequestLimitInterceptor(limiter);
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(flowController())
            .addPathPatterns("/**");
    }



}
