package com.ssm.chapter5.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.chapter5.param.PageParams;
import com.ssm.chapter5.param.RoleParams;
import com.ssm.chapter5.pojo.Role;

public interface RoleMapper {
	
	public Role getRole(Long id);
	
	public List<Role> findRolesByMap(Map<String, Object> parameterMap);
	
	public List<Role> findRolesByAnnotation(@Param("roleName") String rolename, @Param("note") String note);
	
	public List<Role> findRolesByBean(RoleParams roleParam);
	
	public List<Role> findByMix(@Param("params") RoleParams roleParams, @Param("page") PageParams PageParam);
	
	public Role getRoleUseResultMap(Long id);
	
	public int insertRole(Role role);
	
	public int insertRole2(Role role);
	
	public int updateRole(Role role);
	
	public int deleteRole(Long id);
}
