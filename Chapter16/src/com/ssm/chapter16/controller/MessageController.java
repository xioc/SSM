package com.ssm.chapter16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@RequestMapping("/msgpage")
	public String page(Model model) {
		return "msgpage";
	}
}
