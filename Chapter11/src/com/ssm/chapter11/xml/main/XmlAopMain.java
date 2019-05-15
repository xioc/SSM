package com.ssm.chapter11.xml.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.chapter11.aop.verifier.RoleVerifier;
import com.ssm.chapter11.game.pojo.Role;
import com.ssm.chapter11.xml.service.RoleService;

public class XmlAopMain {
	public static void main(String []args) {
		ApplicationContext ctx 
		   = new ClassPathXmlApplicationContext("spring-cfg4.xml");
		RoleService roleService = ctx.getBean(RoleService.class);
		RoleVerifier roleVerifier = (RoleVerifier) roleService;
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("role_name_1");
		role.setNote("note_1");
		if (roleVerifier.verify(role)) { 
		    roleService.printRole(role);
		}
	}
}
