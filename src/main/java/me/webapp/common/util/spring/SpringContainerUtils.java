package me.webapp.common.util.spring;

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
