package com.ssm.chapter15.service;

import java.util.List;

import com.ssm.chapter15.pojo.Role;
import com.ssm.chapter15.pojo.RoleParams;

public interface RoleService {
	
	public int insertRoles(List<Role> roleList);
	
	public Role getRole(Long id);
	
	public List<Role> findRoles(RoleParams roleParams);
	
	public int deleteRoles(List<Long> idList);
	
	public int insertRole(Role role);
}
