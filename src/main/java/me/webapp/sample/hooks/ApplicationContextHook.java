package me.webapp.sample.hooks;

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
import org.springframework.stereotype.Component;

/**
 * 实现了{@link ApplicationContextAware}的类，可以通过{@link #setApplicationContext(ApplicationContext)}获得ApplicationContext对象
 * 这个函数会在ApplicationContext创建之后被调用
 * 实现ApplicationContextAware的类本身需要注册为一个bean，才能被Spring扫描到
 *
 *
 * 实现了{@link ApplicationContextInitializer}的类，可以对{@link ConfigurableApplicationContext}做一些定制化的工作
 * {@link #initialize}要先于{@link #setApplicationContext}被调用
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class ApplicationContextHook implements ApplicationContextAware, ApplicationContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHook.class);
    private ApplicationContext applicationContext;


    /**
     * Invoked after population of normal bean properties but before an init callback
     * such as org.springframework.beans.factory.InitializingBean.afterPropertiesSet()
     * or a custom init-method. Invoked after ResourceLoaderAware.setResourceLoader,
     * ApplicationEventPublisherAware.setApplicationEventPublisher and MessageSourceAware,
     * if applicable.
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("Class implements ApplicationContextAware can get ApplicationContext reference to do something");

        this.applicationContext = applicationContext;
        // cannot access error ???
//        applicationContext.getBean(BeanLifetimeHook.class)
    }


    /**
     * 没有奏效？？？TODO
     *
     * @param applicationContext
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        logger.debug("initialize applicationContext");
    }
}


