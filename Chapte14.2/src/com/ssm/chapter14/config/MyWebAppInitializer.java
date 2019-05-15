package com.ssm.chapter14.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
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

	//DispatchServlet[修改为：DispatcherServlet]拦截内容
	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do" };
	}

}