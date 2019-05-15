package com.ssm.chapter21.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssm.chapter21.config.RedisConfig;
import com.ssm.chapter21.config.RootConfig;
import com.ssm.chapter21.pojo.Role;
import com.ssm.chapter21.service.RoleService;

public class Chapter21Main {

	public static void main(String[] args) {
		//使用注解Spring IoC容器
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class, RedisConfig.class);
		//获取角色服务类
		RoleService roleService = ctx.getBean(RoleService.class);
		Role role = new Role();
		role.setRoleName("role_name_1");
		role.setNote("role_note_1");
		//插入角色
		roleService.insertRole(role);
		//获取角色
		Role getRole = roleService.getRole(role.getId());
		getRole.setNote("role_note_1_update");
		//更新角色
		roleService.updateRole(getRole);
		//删除角色
		roleService.deleteRole(getRole.getId());
	}

}
