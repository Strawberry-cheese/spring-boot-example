//package com.example.tribal.config;
//
//import com.example.tribal.intercepter.AuthenticationInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author lhui    919238538@qq.com:
// * @description
// * @date 2022/7/29 下午3:44
// */
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//        registry.addInterceptor(authenticationInterceptor())
//                .addPathPatterns("/**");
//    }
//
//    @Bean
//    public AuthenticationInterceptor authenticationInterceptor() {
//        return new AuthenticationInterceptor();
//    }
//}
