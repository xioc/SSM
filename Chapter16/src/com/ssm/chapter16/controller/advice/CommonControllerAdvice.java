package com.ssm.chapter16.controller.advice;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

//标识控制器通知，并且指定对应的包
@ControllerAdvice(basePackages={"com.ssm.chapter16.controller.advice"})
public class CommonControllerAdvice {

	//定义HTTP对应参数处理规则
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//针对日期类型的格式化，其中CustomDateEditor是客户自定义编辑器
         //它的boolean参数表示是否允许为空
		binder.registerCustomEditor(Date.class, 
             new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}
 
     //处理数据模型，如果返回对象，则该对象会保存在
	@ModelAttribute
	public void populateModel(Model model) {
		model.addAttribute("projectName", "chapter16");
	}

	//异常处理，使得被拦截的控制器方法发生异常时，都能用相同的视图响应
	@ExceptionHandler(Exception.class)
	public String exception() {
		return "exception";
	}

}
