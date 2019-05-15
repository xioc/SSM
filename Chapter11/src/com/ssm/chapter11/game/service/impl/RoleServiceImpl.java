package com.ssm.chapter11.game.service.impl;

import com.ssm.chapter11.game.pojo.Role;
import com.ssm.chapter11.game.service.RoleService;

public class RoleServiceImpl implements RoleService {
	@Override
	public void printRole(Role role) {
		System.out.println(
				"{id =" + role.getId() + ", roleName=" + role.getRoleName() + ", note=" + role.getNote() + "}");
	}
}