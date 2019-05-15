package com.ssm.chapter10.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssm.chapter10.annotation.config.ApplicationConfig;
import com.ssm.chapter10.annotation.config.AutowiredConfig;
import com.ssm.chapter10.annotation.controller.RoleController;
import com.ssm.chapter10.annotation.controller.RoleController2;
import com.ssm.chapter10.annotation.pojo.PojoConfig;
import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleDataSourceService;
import com.ssm.chapter10.annotation.service.RoleService;
import com.ssm.chapter10.annotation.service.RoleService2;

public class AnnotationMain {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8() ;
		test9();
	}

	private static void test1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(PojoConfig.class);
		Role role = context.getBean(Role.class);
		System.err.println(role.getId());
	}

	private static void test2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Role role = context.getBean(Role.class);
		RoleService roleService = context.getBean("roleService3", RoleService.class);
		roleService.printRoleInfo(role);
		context.close();
	}
	
	private static void test3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleService2 roleService = context.getBean(RoleService2.class);
		roleService.printRoleInfo();
		context.close();
	}
	
	private static void test4() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfig.class);
		RoleController roleController = context.getBean(RoleController.class);
		Role role = context.getBean(Role.class);
		roleController.printRole(role);
		context.close();
	}
	
	private static void test5() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfig.class);
		RoleController2 roleController = context.getBean(RoleController2.class);
		Role role = context.getBean(Role.class);
		roleController.printRole(role);
		context.close();
	}
	
	private static void test6() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DataSource dataSource = context.getBean(DataSource.class);
		try {
			System.out.println(dataSource.getConnection().getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void test7() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		context.close();
	}
	
	
	/***
	 * 测试注解引入XML时候，注意到ApplicationConfig关于这个方法的注释，需要修改后才能测试。 
	 */
	private static void test8() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDataSourceService roleDataSourceService = context.getBean(RoleDataSourceService.class);
		Role role = roleDataSourceService.getRole(1L);
		System.out.println(role.getRoleName());
		context.close();
	}
	
	private static void test9() {
		ApplicationContext context = 
			     new AnnotationConfigApplicationContext(ApplicationConfig.class);
			String url = context.getEnvironment().getProperty("jdbc.database.url");
			System.out.println(url);
	}
}
