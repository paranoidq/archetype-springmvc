package me.webapp.support.async;

import me.webapp.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定制化的异步执行器，执行Spring的异步任务
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@EnableAsync
public class AsyncTaskExecutorConfig implements AsyncConfigurer {
    private static Logger logger = LoggerFactory.getLogger(AsyncTaskExecutorConfig.class);


    @Autowired
    private AppConfig appConfig;


    @Bean()
    @Primary
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(appConfig.getAsyncExecutorCorePoolSize());
        executor.setMaxPoolSize(appConfig.getAsyncExecutorMaxPoolSize());
        executor.setKeepAliveSeconds(appConfig.getAsyncExecutorKeepAliveSeconds());
        executor.setQueueCapacity(appConfig.getAsyncExecutorQueueCapacity());
        executor.setAwaitTerminationSeconds(appConfig.getAsyncExecutorAwaitTerminationSeconds());
        executor.setThreadNamePrefix("SpringAsyncExecutor");
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.error("Rejected Task: " + r);
            }
        });
        executor.initialize();  // 初始化executor
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                logger.error("Async任务执行失败", ex);
            }
        };
    }
}
