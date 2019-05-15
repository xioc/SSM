package com.ssm.chapter13.mapper;

import com.ssm.chapter13.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
	public int insertRole(Role role);
}