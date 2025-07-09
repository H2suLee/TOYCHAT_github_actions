package com.toychat.prj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/{spring:(?!api|ws|js|css|img|fonts|favicon\\.ico|.*\\..*).*}")
				.setViewName("forward:/index.html");
		registry.addViewController("/**/{spring:(?!api|ws|js|css|img|fonts|favicon\\.ico|.*\\..*).*}")
				.setViewName("forward:/index.html");
		// registry.addViewController("/") // Spring boot가 / 는 /index.html 로 자동 반환하기 때문에 안써도 됨
		
	}
}
