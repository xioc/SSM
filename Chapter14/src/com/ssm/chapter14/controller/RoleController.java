package com.ssm.chapter14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ssm.chapter14.pojo.Role;
import com.ssm.chapter14.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	// 注入角色服务类
	@Autowired
	private RoleService roleService = null;

	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public ModelAndView getRole(@RequestParam("id") Long id) {
		Role role = roleService.getRole(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roleDetails");
		// 给数据模型添加一个角色对象
		mv.addObject("role", role);
		return mv;
	}

	// 获取角色
	@RequestMapping(value = "/getRole2", method = RequestMethod.GET)
	public ModelAndView getRole2(@RequestParam("id") Long id) {
		Role role = roleService.getRole(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("role", role);
		// 指定视图类型
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
}