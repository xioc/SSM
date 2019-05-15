package com.ssm.chapter15.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("/file")
public class FileController implements ApplicationContextAware  {

	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request) {
		// 进行转换
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
		// 获得请求上传的文件
		MultipartFile file = mhsr.getFile("file");
		// 设置视图为JSON视图
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		// 获取原始文件名
		String fileName = file.getOriginalFilename();
		// 目标文件
		File dest = new File(fileName);
		try {
			// 保存文件
			file.transferTo(dest);
			// 保存成功
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		} catch (IllegalStateException | IOException e) {
			// 保存失败
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}

	// 使用MultipartFile
	@RequestMapping("/uploadMultipartFile")
	public ModelAndView uploadMultipartFile(MultipartFile file) {
		// 定义JSON视图
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		// 获取原始文件名
		String fileName = file.getOriginalFilename();
		file.getContentType();
		// 目标文件
		File dest = new File(fileName);
		try {
			// 保存文件
			file.transferTo(dest);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		} catch (IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}

	// 使用Part
	@RequestMapping(value="/uploadPart", method=RequestMethod.POST)
	public ModelAndView uploadPart(Part file) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		// 获取原始文件名
		String fileName = file.getSubmittedFileName();
		File dest = new File(fileName);
		try {
			// 保存文件
			file.write("e:/mvc/uploads/" + fileName);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		} catch (IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}

	private DefaultFormattingConversionService conversionService = null;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		String beanName = "mvcConversionService";
		conversionService = (DefaultFormattingConversionService)ctx.getBean(beanName);
		System.out.println(conversionService.getClass().getName());
	}
	
	public static void main(String[] args) {
		double result = (30000*0.08*79+10000*0.09*79+40000*0.10*79)*0.888;
		System.out.println(result/30);
	}
}