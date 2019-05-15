package com.ssm.chapter16.service;

import java.util.List;

import com.ssm.chapter16.pojo.Role;

public interface RoleService {
	
	
	public Role getRole(Long id);
	
	public int updateRole(Role role);
	
	public int updateRoleArr(List<Role> roleList);
	
}
