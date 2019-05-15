package com.ssm.chapter15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ssm.chapter15.pojo.Role;
import com.ssm.chapter15.service.RoleService;

@Controller
@RequestMapping("/attribute")
//可以配置数据模型的名称和类型，两者取或关系
@SessionAttributes(names ={"id"}, types = { Role.class })
public class AttributeController {
	
	@Autowired
	private RoleService roleService = null;
	
	@RequestMapping("/requestAttribute")
	public ModelAndView reqAttr(@RequestAttribute("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRole(id);
		mv.addObject("role", role);
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
	
	@RequestMapping("/sessionAttributes")
	public ModelAndView sessionAttrs(Long id) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRole(id);
        //根据类型，session将会保存角色信息
		mv.addObject("role", role); 
        //根据名称，session将会保存id
		mv.addObject("id", id);
        //视图名称，定义跳转到一个JSP文件上
		mv.setViewName("sessionAttribute");
		return mv;
	}
	
	@RequestMapping("/sessionAttribute")
	public ModelAndView sessionAttr(@SessionAttribute("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRole(id);
		mv.addObject("role", role);
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
	
	@RequestMapping("/getHeaderAndCookie")
	public String testHeaderAndCookie(
		@RequestHeader(value="User-Agent", required = false, defaultValue = "attribute")
	         String userAgent,
		@CookieValue(value = "JSESSIONID", required = true, defaultValue = "MyJsessionId") 
	         String jsessionId) {
		System.out.println("User-Agent：" + userAgent);
		System.out.println("JSESSIONID：" + jsessionId);
		return "index";
	}
}
