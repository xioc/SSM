package com.ssm.chapter10.annotation.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService;

@Component("roleService3")
//@Primary
public class RoleServiceImpl3 implements RoleService {
	
	@Override
	public void printRoleInfo(Role role) {
		System.out.print("{id =" + role.getId());
		System.out.print(", roleName =" + role.getRoleName());
		System.out.println(", note =" + role.getNote() + "}");
	}
}