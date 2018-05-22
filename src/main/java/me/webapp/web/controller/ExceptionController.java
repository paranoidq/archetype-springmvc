package me.webapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class ExceptionController {

    @RequestMapping("/exception")
    public String f() throws Throwable {
        throw new Throwable("a");
    }
}
