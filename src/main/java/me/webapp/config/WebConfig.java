//package me.webapp.config;
//
//import me.webapp.support.statistics.EnableMethodTiming;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.format.datetime.DateFormatter;
//import org.springframework.http.CacheControl;
//import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
//import org.springframework.web.context.request.async.TimeoutDeferredResultProcessingInterceptor;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.resource.GzipResourceResolver;
//import org.springframework.web.servlet.resource.VersionResourceResolver;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author paranoidq
// * @since 1.0.0
// */
//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    // TODO: or WebMvcConfigurerSupport?????
//    /**
//     * 添加格式化转换器
//     *
//     * @param registry
//     */
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//
//    }
//
//
//    /**
//     * 配置静态资源处理
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("resources/**")
//            .addResourceLocations("/resources/")
//            .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
//            .setCachePeriod(31556926)
//            .resourceChain(true)
//                .addResolver(new GzipResourceResolver())
//                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//        // TODO：js单独配置
//        // TODO: 静态资源的version的问题
//    }
//
//
//    /**
//     * 配置springmvc中的异步支持，启用异步支持需要在web.xml开启
//     * @param configurer
//     */
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer
//            .setDefaultTimeout(10000)
//            .registerCallableInterceptors(new TimeoutCallableProcessingInterceptor())
//            .registerDeferredResultInterceptors(new TimeoutDeferredResultProcessingInterceptor())
//            .setTaskExecutor(new SimpleAsyncTaskExecutor("asyncTaskExecutor"));
//    }
//}
