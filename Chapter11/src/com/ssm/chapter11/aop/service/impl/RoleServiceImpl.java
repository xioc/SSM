package com.ssm.chapter11.aop.service.impl;

import org.springframework.stereotype.Component;

import com.ssm.chapter11.aop.service.RoleService;
import com.ssm.chapter11.game.pojo.Role;

@Component
public class RoleServiceImpl implements RoleService {
	
	@Override
	public void printRole(Role role) {
		System.out.println("{id: " + role.getId() + ", " 
	        + "role_name : " + role.getRoleName() + ", "
	        + "note : " + role.getNote() + "}");
	}
	
	@Override
	public void printRole(Role role, int sort) {
		System.out.println("{id: " + role.getId() + ", " 
	        + "role_name : " + role.getRoleName() + ", "
	        + "note : " + role.getNote() + "}");
		System.out.println(sort);
	}
}
