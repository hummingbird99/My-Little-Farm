package com.example.choi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("user/main");
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/admin").setViewName("user/admin");
        registry.addViewController("/signup").setViewName("user/signup");

        registry.addViewController("/MyPage").setViewName("/MyPage");
    }
}