package com.ssm.chapter14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//定义扫描的包，加载控制器
@ComponentScan("com.*")
//启用Spring Web MVC
@EnableWebMvc
public class WebConfig { 
	/***
	 * 创建视图解析器
	 * @return 视图解析器
	 */
	@Bean(name="viewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver viewResolver =new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}	
}