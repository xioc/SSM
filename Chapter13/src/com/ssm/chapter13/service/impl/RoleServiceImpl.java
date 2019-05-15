package com.ssm.chapter13.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.chapter13.mapper.RoleMapper;
import com.ssm.chapter13.pojo.Role;
import com.ssm.chapter13.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService, ApplicationContextAware {
	
	@Autowired
	private RoleMapper roleMapper = null;
	
	
	private ApplicationContext ctx = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public int insertRole(Role role) {
		return roleMapper.insertRole(role);
	}
	
	
    //自调用问题
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for (Role role : roleList) {
			try {
                //调用自身类的方法，产生自调用问题
				insertRole(role);
				count++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}
	
	//消除自调用问题
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED)
	public int insertRoleList2(List<Role> roleList) {
		int count = 0;
		//从容器中获取RoleService对象，实际是一个代理对象
		RoleService service = ctx.getBean(RoleService.class);
		for (Role role : roleList) {
			try {
				service.insertRole(role);
				count++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	
	//使用生命周期的接口方法，获取IoC容器
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
	}
}