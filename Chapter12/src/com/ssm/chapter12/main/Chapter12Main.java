package com.ssm.chapter12.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ssm.chapter12.jdbc.JdbcExample;
import com.ssm.chapter12.mapper.RoleMapper;
import com.ssm.chapter12.pojo.Role;

public class Chapter12Main {

	public static void main(String[] args) {
//		testJdbcExample();
//		tesSpring();
//		testSqlSessionTemplate();
//		testRoleMapper() ;
		double result = (119*10000*0.12 + 119*(10000-4000)*0.11)*0.94;
		System.out.println(result*0.88);
	}

	public static void testJdbcExample() {
		JdbcExample exp = new JdbcExample();
		Role role = exp.getRole(1L);
		System.out.println(role.getRoleName());
	}

	public static void tesSpring() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		Long id = 1L;
		String sql = "select id, role_name, note from t_role where id = " + id;
		Role role = jdbcTemplate.queryForObject(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rownum) throws SQLException {
				Role result = new Role();
				result.setId(rs.getLong("id"));
				result.setRoleName(rs.getString("role_name"));
				result.setNote(rs.getString("note"));
				return result;
			}
		});
//		Role role = jdbcTemplate.queryForObject(sql, (ResultSet rs, int rownum) -> {
//			Role result = new Role();
//			result.setId(rs.getLong("id"));
//			result.setRoleName(rs.getString("role_name"));
//			result.setNote(rs.getString("note"));
//			return result;
//		});
		System.out.println(role.getRoleName());
	}
	
	public static void testSqlSessionTemplate() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
		//ctxÎªSpring IoCÈÝÆ÷
		SqlSessionTemplate sqlSessionTemplate = ctx.getBean(SqlSessionTemplate.class);
		Role role = new Role();
		role.setRoleName("role_name_sqlSessionTemplate");
		role.setNote("note_sqlSessionTemplate");
		sqlSessionTemplate.insert("com.ssm.chapter12.mapper.RoleMapper.insertRole", role);
		Long id = role.getId();
		sqlSessionTemplate.selectOne("com.ssm.chapter12.mapper.RoleMapper.getRole", id);
		role.setNote("update_sqlSessionTemplate");
		sqlSessionTemplate.update("com.ssm.chapter12.mapper.RoleMapper.updateRole", role);
		sqlSessionTemplate.delete("com.ssm.chapter12.mapper.RoleMapper.deleteRole", id);
	}
	
	public static void testRoleMapper() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
		RoleMapper roleMapper = ctx.getBean(RoleMapper.class);
		roleMapper.getRole(2L);
	}
}
