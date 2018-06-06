package me.webapp.web.open.rest;

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

import me.webapp.common.util.spring.SpringContainerUtils;
import me.webapp.support.auth.AuthCheck;
import me.webapp.service.UserService;
import me.webapp.support.statistics.MethodLogging;
import me.webapp.support.statistics.MethodTiming;
import me.webapp.web.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpringContainerUtils containerUtils;

    @RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = "*/*")
    @ResponseBody
//    @MethodLogging
//    @MethodTiming
//    @AuthCheck
    public ApiResponse hello() {

        ResourceLoader resourceLoader = containerUtils.getResourceLoader();
        ApplicationContext context = containerUtils.getApplicationContext();

        assert resourceLoader != null;
        assert context != null;

        // call service method
        userService.test();

        return ApiResponse.createOk("hello");
    }
}
