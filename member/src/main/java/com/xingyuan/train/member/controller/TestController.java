package com.xingyuan.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 13:10
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
