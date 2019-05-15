package com.ssm.chapter14.dao;

import org.springframework.stereotype.Repository;

import com.ssm.chapter14.pojo.Role;

@Repository
public interface RoleDao {
	
	public Role getRole(Long id);
}
