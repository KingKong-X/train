package com.xingyuan.train.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 13:07
 */
@SpringBootApplication
@ComponentScan("com.xingyuan")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
