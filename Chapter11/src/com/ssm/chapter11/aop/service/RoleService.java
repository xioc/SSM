package com.ssm.chapter11.aop.service;

import com.ssm.chapter11.game.pojo.Role;

public interface RoleService {
	
	public void printRole(Role role);
	
	public void printRole(Role role, int sort);
}
