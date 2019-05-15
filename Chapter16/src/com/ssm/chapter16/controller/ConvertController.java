package com.ssm.chapter16.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.chapter16.pojo.FormatPojo;

@Controller
@RequestMapping("/convert")
public class ConvertController {

	@RequestMapping("/format")
	public ModelAndView format(
		//日期格式化
		@RequestParam("date1") @DateTimeFormat(iso = ISO.DATE) Date date,
		//金额格式化
		@RequestParam("amount1") @NumberFormat(pattern = "#,###.##") Double amount) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("date", date);
		mv.addObject("amount", amount);
		return mv;
	}
	
	@RequestMapping("/formatPojo")
	public ModelAndView formatPojo(FormatPojo pojo) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("date", pojo.getDate1());
		mv.addObject("amount", pojo.getAmount1());
		return mv;
	}
}