package com.ssm.chapter15.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.chapter15.pojo.Transaction;
import com.ssm.chapter15.validator.TransactionValidator;

@Controller
@RequestMapping("/validate")
public class ValidateController {

	@RequestMapping("/annotation")
	public ModelAndView annotationValidate(@Valid Transaction trans, Errors errors) {
		// 是否存在错误
		if (errors.hasErrors()) {
			// 获取错误信息
			List<FieldError> errorList = errors.getFieldErrors();
			for (FieldError error : errorList) {
				// 打印字段错误信息
				System.err.println("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@InitBinder
	public void initBinder(DataBinder binder) {
		// 数据绑定器加入验证器
		binder.setValidator(new TransactionValidator());
	}

	@RequestMapping("/validator")
	public ModelAndView validator(@Valid Transaction trans, Errors errors) {
		// 是否存在错误
		if (errors.hasErrors()) {
			// 获取错误信息
			List<FieldError> errorList = errors.getFieldErrors();
			for (FieldError error : errorList) {
				// 打印字段错误信息
				System.err.println("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}
