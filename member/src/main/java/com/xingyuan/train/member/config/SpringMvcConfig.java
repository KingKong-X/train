package com.xingyuan.train.member.config;

import com.xingyuan.train.common.interceptor.LogInterceptor;
import com.xingyuan.train.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xingyuan Huang
 * @since 2023/7/10 20:44
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    MemberInterceptor memberInterceptor;

    @Resource
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/hello",
                        "/member/send-code",
                        "/member/login"
                );
    }
}
