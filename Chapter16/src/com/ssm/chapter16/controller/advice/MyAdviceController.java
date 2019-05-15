package com.ssm.chapter16.controller.advice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/advice")
public class MyAdviceController {
	/***
	 * 
	 * @param date 日期，在@initBinder 绑定的方法有注册格式
	 * @param model 数据模型，@ModelAttribute方法会先于请求方法运行
	 * @return map
	 */
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> testAdvice(Date date, @NumberFormat(pattern = "##,###.00") BigDecimal amount, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		//由于@ModelAttribute注解的通知会在控制器方法前运行，所以这样也会取到数据
		map.put("project_name", model.asMap().get("projectName"));
		map.put("date", DateUtils.format(date, "yyyy-MM-dd"));
		map.put("amount", amount);
		return map;
	}
	
	/**
	 * 测试异常.
	 */
	@RequestMapping("/exception")
	public void exception() {
		throw new RuntimeException("测试异常跳转");
	}
}
