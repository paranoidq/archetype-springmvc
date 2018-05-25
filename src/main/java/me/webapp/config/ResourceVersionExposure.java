package me.webapp.config;

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
