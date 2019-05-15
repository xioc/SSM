package com.ssm.chapter16.dao;


import org.springframework.stereotype.Repository;

import com.ssm.chapter16.pojo.Role;

@Repository
public interface RoleDao {
	
	
	public Role getRole(Long id);
	
	public int updateRole(Role role);
}
