package com.ssm.chapter11.game;

import com.ssm.chapter11.game.interceptor.RoleInterceptor;
import com.ssm.chapter11.game.pojo.Role;
import com.ssm.chapter11.game.service.RoleService;
import com.ssm.chapter11.game.service.impl.RoleServiceImpl;

public class GameMain {
	public static void main(String[] args) {
		RoleService roleService = new RoleServiceImpl();
		Interceptor interceptor = new RoleInterceptor();
		RoleService proxy = ProxyBeanFactory.getBean(roleService, interceptor);
		Role role = new Role(1L, "role_name_1", "role_note_1");
		proxy.printRole(role);
		System.out.println("##############测试afterthrowing方法###############");
		role = null;
		proxy.printRole(role);
	}
}
