package com.ssm.chapter21.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssm.chapter21.pojo.Role;

/**** imports ****/
@Repository
public interface RoleDao {

	public Role getRole(Long id);

	public int deleteRole(Long id);

	public int insertRole(Role role);

	public int updateRole(Role role);

	public List<Role> findRoles(@Param("roleName") String roleName, @Param("note") String note);
}