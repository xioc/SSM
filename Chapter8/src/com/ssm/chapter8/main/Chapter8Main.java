package com.ssm.chapter8.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ssm.chapter8.mapper.RoleMapper;
import com.ssm.chapter8.params.PageParams;
import com.ssm.chapter8.pojo.Role;
import com.ssm.chapter8.utils.SqlSessionFactoryUtils;

public class Chapter8Main {

	public static void main(String[] args) {
		testMyPlugin();
//		testPagePlugin();
	}

	private static void testMyPlugin() {
		Logger log = Logger.getLogger(Chapter8Main.class);
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtils.openSqlSession();
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(1L);
			log.info(role.getRoleName());
		} catch (Exception ex) {
		    ex.printStackTrace();
		    sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	
	//测试时，请在配置文件mybatis-config.xml中注释掉插件MyPlugin
	private static void testPagePlugin() {
		Logger log = Logger.getLogger(Chapter8Main.class); 
		SqlSession sqlSession = null;
		try {
		    sqlSession = SqlSessionFactoryUtils.openSqlSession();
		    RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		    PageParams pageParams = new PageParams();
		    pageParams.setPageSize(10);
		    List <Role> roleList = roleMapper.findRole(pageParams, "role_name_");
		    log.info(roleList.size());
		} catch (Exception ex) {
		    ex.printStackTrace();
		    sqlSession.rollback();
		} finally {
		    if (sqlSession != null) {
		        sqlSession.close();
		    }
		}
	}

}