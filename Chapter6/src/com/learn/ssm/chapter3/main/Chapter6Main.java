package com.learn.ssm.chapter3.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.learn.ssm.chapter3.utils.SqlSessionFactoryUtils;
import com.ssm.chapter6.mapper.RoleMapper;
import com.ssm.chapter6.pojo.Role;

public class Chapter6Main {

	public static void main(String[] args) {
		testFindRole1();
		testFindRole2();
		testFindRole3();
		testFindRole4();
		testFindRole5();
		testFindRole6();
		testUpdateRole();
		testFindRoleByNums();
		testGetRoleTest();
	}

	public static void testFindRole1() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.findRole1("role_name");
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testFindRole2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setRoleNo("role_no_1");
			role.setRoleName("role_name");
			List<Role> roleList = roleMapper.findRole2(role);
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testFindRole3() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setRoleNo("role_no_1");
			role.setRoleName("role_name");
			List<Role> roleList = roleMapper.findRole3(role);
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testFindRole4() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.findRole4("role_name");
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testFindRole5() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.findRole5("role_name");
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testFindRole6() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.findRole6("role_name", "note");
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public static void testUpdateRole() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setNote("note_1_update");
			role.setRoleName("role_name_1_update");
			role.setRoleNo("role_no_1");
			int result = roleMapper.updateRole(role);
			System.out.println(result);
			sqlSession.commit();
		} catch(Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public static void testFindRoleByNums() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<String> roleNoList = new ArrayList<String>();
			roleNoList.add("role_no_1");
			roleNoList.add("role_no_2");
			roleNoList.add("role_no_3");
			List<Role> roleList = roleMapper.findRoleByNums(roleNoList);
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void testGetRoleTest() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.getRoleTest("Y");
			System.out.println(roleList.size());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}