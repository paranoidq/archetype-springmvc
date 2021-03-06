package me.webapp.sample.event;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * 监听ApplicationEvent事件
 *
 * 方式1：实现ApplicationListener接口
 * 方式2：注解{@link EventListener}
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class ApplicationEventHook implements ApplicationListener, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventHook.class);

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 如果Bean想发布事件，则Bean必须获得其容器的引用。
     * 如果程序中没有直接获取容器的引用，则应该让Bean实现{@link ApplicationContextAware}或者{@link org.springframework.beans.factory.BeanFactoryAware}接口，从而可以获得容器的引用
     */
    public void publishApplicationEvent() {
        applicationContext.publishEvent(new MyEvent("myEvent"));
    }


    /**
     * 实现{@link #onApplicationEvent(ApplicationEvent)}方法，并在内部判断具体的event类型
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent) {
            logger.info("myEvent has been received");
        }
    }


    /**
     * ApplicationContext容器初始化或刷新时触发该事件
     *
     * 初始化：所有的Bean被成功装载，后处理Bean被检测并激活，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用
     *
     * 在容器启动过程中，可能会多次触发该事件
     *
     * @param refreshedEvent
     */
    @EventListener(classes = ContextRefreshedEvent.class)
    public void handleContextRefreshEvent(ContextRefreshedEvent refreshedEvent) {
        logger.info("xxxxxx" + refreshedEvent.getApplicationContext().toString());
    }

    /**
     * 当使用ConfigurableApplicationContext(ApplicationContext的子接口）接口的start()方法启动ApplicationContext容器时触发该事件
     *
     * @param startedEvent
     */
    @EventListener(classes = ContextStartedEvent.class)
    public void handleContextStartedEvent(ContextStartedEvent startedEvent) {

    }

    /**
     * 当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext时触发该事件
     *
      * @param closedEvent
     */
    @EventListener(classes = ContextClosedEvent.class)
    public void handleContextClosedEvent(ContextClosedEvent closedEvent) {

    }

    /**
     * 当使用ConfigurableApplicationContext接口的stop()方法使ApplicationContext容器停止时触发该事件。
     * 此处的停止，意味着容器管理生命周期的Bean实例将获得一个指定的停止信号，被停止的Spring容器可再次调用start()方法重新启动
     *
     *
     * @param stoppedEvent
     */
    @EventListener(classes = ContextStoppedEvent.class)
    public void handleContextStoppedEvent(ContextStoppedEvent stoppedEvent) {

    }


    /**
     * Web相关事件，只能应用于使用DispatcherServlet的Web应用。在使用Spring作为前端的MVC控制器时，当Spring处理用户请求结束后，系统会自动触发该事件
     * @param requestHandledEvent
     */
    @EventListener(classes = RequestHandledEvent.class)
    void handlerRequestHandledEvent(RequestHandledEvent requestHandledEvent) {

    }



}
