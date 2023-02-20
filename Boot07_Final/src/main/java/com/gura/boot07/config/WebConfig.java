package com.gura.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gura.boot07.interceptor.LoginInterceptor;
import com.gura.boot07.interceptor.MobileLoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//로그인 인터셉터 주입받기
	@Autowired LoginInterceptor loginInterceptor;
	@Autowired MobileLoginInterceptor mLoginInterceptor;
	
	//인터셉터 동작하도록 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//웹브라우저의 요청에 대해 개입할 인터셉터
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/users/*", "/gallery/*", "/gallery1/*", "/file/*", "/cafe/*","/music/*")
		.excludePathPatterns("/users/signup_form", "/users/signup", "/users/loginform", "/users/login", "/users/profile/*",
				"/gallery/list", "/gallery/detail", "/gallery1/list", "/gallery1/detail", "/gallery/images/*", "/gallery1/images/*",
				"/file/list", "/file/download", "/cafe/list", "/cafe/detail", "/cafe/ajax_comment_list", "/music/login");
		
		//모바일 요청에 대해 개입할 인터셉터
		registry.addInterceptor(mLoginInterceptor)
		.addPathPatterns("/api/gallery/*");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
