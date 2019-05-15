package com.ssm.chapter16.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.ssm.chapter16.converter.StringToRoleConverter;

@Service
public class WebConfig {
	//自定义转换器列表
	private List<Converter> myConverter = null;

	//依赖注入FormattingConversionServiceFactoryBean对象，它是一个自动初始化的对象
	@Autowired
	private FormattingConversionServiceFactoryBean fcsfb = null;

	@Bean(name = "myConverter")
	public List<Converter> initMyConverter() {
	    if (myConverter == null) {
	        myConverter = new ArrayList<Converter>();
	    }
	    //自定义的字符串和角色转换器
	    Converter roleConverter = new StringToRoleConverter();
	    myConverter.add(roleConverter);
	    //往转换服务类注册转换器
	    fcsfb.getObject().addConverter(roleConverter);
	    return myConverter;
	}
	
	@Bean(name="messageSource")
	public MessageSource initMessageSource() {
		ReloadableResourceBundleMessageSource msgSrc =
	        new ReloadableResourceBundleMessageSource();
		msgSrc.setDefaultEncoding("UTF-8");
		msgSrc.setBasename("classpath:msg");
	    //缓存3 600秒,相当于1小时，然后重新刷新
	    msgSrc.setCacheSeconds(3600);
	    //缓存3 600×1 000毫秒，相当于1小时，然后重新刷新
	    //msgSrc.setCacheMillis(3600*1000);
		return msgSrc;
	}
	
//	@Bean(name="messageSource")
//	public MessageSource initMessageSource() {
//		ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
//		msgSrc.setDefaultEncoding("UTF-8");
//		msgSrc.setBasename("msg");
//		return msgSrc;
//	}
	
//	@Bean(name="localeResolver")
//	public LocaleResolver initCookieLocaleResolver() {
//		CookieLocaleResolver clr = new CookieLocaleResolver();
//		//cookie名称
//		clr.setCookieName("lang");
//		//cookie超时秒数
//		clr.setCookieMaxAge(1800);
//		//默认使用简体中文
//		clr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//		return clr;
//	}
	
	
	@Bean(name="localeResolver")
	public LocaleResolver initSessionLocaleResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		//默认使用简体中文
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return slr;
	}
}
