package com.ssm.chapter15.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
	//Spring IoC容器配置
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//可以返回Spring的Java配置文件数组
		return new Class<?>[] {};
	}

	//DispatcherServlet的URI映射关系配置
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//可以返回Spring的Java配置文件数组
		return new Class<?>[] { WebConfig.class };
	}

	//DispatchServlet[修改为：DispatcherServlet]拦截请求匹配
	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do" };
	}
	
	/**
	 * @param dynamic Servlet动态加载配置
	 */
	@Override
	protected void customizeRegistration(Dynamic dynamic) {
		//文件上传路径
		String filepath = "e:/mvc/uploads";
		//5MB
		Long singleMax = (long) (5*Math.pow(2, 20));
		//10MB
		Long totalMax = (long) (10*Math.pow(2, 20));
		//配置MultipartResolver，限制请求，单个文件5MB，总共文件10MB
		dynamic.setMultipartConfig(new MultipartConfigElement(filepath, singleMax, totalMax, 0));
	}

}