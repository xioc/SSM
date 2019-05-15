package com.ssm.chapter16.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.chapter16.dao.RoleDao;
import com.ssm.chapter16.pojo.Role;
import com.ssm.chapter16.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao = null;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	
	@Override
	public int updateRoleArr(List<Role> roleArr) {
		int count = 0;
		for (Role role : roleArr) {
			count += this.updateRole(role);
		}
		return count;
	}


}
