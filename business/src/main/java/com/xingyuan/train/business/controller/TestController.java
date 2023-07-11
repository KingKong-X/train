package com.xingyuan.train.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xingyuan Huang
 * @since 2023/7/11 19:46
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Business";
    }
}
