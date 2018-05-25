package me.webapp.common.util.customs.api;

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

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 条件删选
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    /**
     * 路径中的版本前缀，用/v[1-9].[1-9]/的形式
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)\\.(\\d+)/");

    /**
     * 版本号
     */
    private double apiVersion;

    public ApiVersionRequestCondition(double apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 将不同的删选条件合并
     * 最后定义优先，方法上定义的ApiVersion覆盖类上的定义
     *
     * @param other
     * @return
     */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
        return new ApiVersionRequestCondition(other.getApiVersion());
    }

    /**
     * 根据request查找匹配的删选条件
     * @param request
     * @return
     */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getPathInfo());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            // 如果请求版本号大于配置版本号，则满足
            if (version >= this.apiVersion) {
                return this;
            }
        }
        return null;
    }

    /**
     * 不同的删选条件比较，用于排序
     * 优先匹配最新版本号
     * @param other
     * @param request
     * @return
     */
    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        return Double.compare(other.getApiVersion(), apiVersion);
    }

    public double getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(double apiVersion) {
        this.apiVersion = apiVersion;
    }
}
