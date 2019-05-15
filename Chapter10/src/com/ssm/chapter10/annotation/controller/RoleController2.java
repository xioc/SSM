package com.ssm.chapter10.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService;


@Component
public class RoleController2 {
	
	private RoleService roleService = null;
	
	public RoleController2(@Autowired @Qualifier("roleService3") RoleService roleService) {
	    this.roleService = roleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService( RoleService roleService) {
		this.roleService = roleService;
	}
	
	public void printRole(Role role) {
		roleService.printRoleInfo(role);
	}
}
