package me.webapp.web.portal.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paranoidq
 * @since 1.0.0
 */

@RestController(value = "/rest")
public class BaseRestController {


    @RequestMapping()
    public String index() {
        return "hello world";
    }
}
