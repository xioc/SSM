package com.ssm.chapter14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

//娉ㄨВ@Controller琛ㄧず瀹冩槸涓�涓帶鍒跺櫒
@Controller("myController")
// 琛ㄦ槑褰撹姹傜殑URI鍦�/my涓嬬殑鏃跺�欐墠鏈夎鎺у埗鍣ㄥ搷搴�
@RequestMapping("/my")
public class MyController {
	
	// 琛ㄦ槑URI鏄�/index鐨勬椂鍊欒鏂规硶鎵嶈姹�
	@RequestMapping("/index")
	public ModelAndView index() {
		// 妯″瀷鍜岃鍥�
		ModelAndView mv = new ModelAndView();
		// 瑙嗗浘閫昏緫鍚嶇О涓篿ndex
		mv.setViewName("index");
		// 杩斿洖妯″瀷鍜岃鍥�
		return mv;
	}

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView index2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index3", method=RequestMethod.GET)
	public ModelAndView index2(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();  
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index4", method=RequestMethod.GET)
	public ModelAndView index2(@RequestParam("id") Long id) {
		System.out.println("params[id] = " + id);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index5", method=RequestMethod.GET)
	public ModelAndView index2(@SessionAttribute("userName") String userName) {
		System.out.println("session[userName] = " + userName);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}