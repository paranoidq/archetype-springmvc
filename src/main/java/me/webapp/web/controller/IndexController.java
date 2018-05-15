package me.webapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/hello", produces = "text/plain; charset=utf-8", consumes = "*/*")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
}
