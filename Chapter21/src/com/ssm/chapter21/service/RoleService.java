package com.ssm.chapter21.service;

import java.util.List;

import com.ssm.chapter21.pojo.Role;

public interface RoleService {
	public Role getRole(Long id);

	public int deleteRole(Long id);

	public Role insertRole(Role role);

	public int updateRole(Role role);

	public List<Role> findRoles(String roleName, String note);
	
	public int insertRoles(List<Role> roleList);
}