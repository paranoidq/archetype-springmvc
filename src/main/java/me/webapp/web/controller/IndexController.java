package me.webapp.web.controller;

import me.webapp.common.util.spring.SpringContainerUtils;
import me.webapp.log.AppLoggerDef;
import me.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpringContainerUtils containerUtils;

    @RequestMapping(value = "/hello", produces = "text/plain; charset=utf-8", consumes = "*/*")
    @ResponseBody
    public String hello() {

        ResourceLoader resourceLoader = containerUtils.getResourceLoader();
        ApplicationContext context = containerUtils.getApplicationContext();

        assert resourceLoader != null;
        assert context != null;

        // call service method
        userService.test();

        return "hello world";
    }
}
