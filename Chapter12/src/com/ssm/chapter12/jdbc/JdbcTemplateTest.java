package com.ssm.chapter12.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ssm.chapter12.pojo.Role;

public class JdbcTemplateTest {
	public static void main(String[] args) {
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
	    JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
	    
	    JdbcTemplateTest test = new JdbcTemplateTest();
	    test.getRoleByConnectionCallback(jdbcTemplate, 1L);
	    test.getRoleByStatementCallback(jdbcTemplate, 1L);
	    test.insertRole(jdbcTemplate);
	    List roleList = test.findRole(jdbcTemplate, "role");
	    System.out.println(roleList.size());
	    Role role = new Role();
	    role.setId(1L);
	    role.setRoleName("update_role_name_1");
	    role.setNote("update_note_1");
	    test.updateRole(jdbcTemplate, role);
	    test.deleteRole(jdbcTemplate, 1L);
	}


	/***
	 * 插入角色
	 * @param jdbcTemplate --模板
	 * @return 影响条数
	 */
	public int insertRole(JdbcTemplate jdbcTemplate) {
	    String roleName = "role_name_1";
	    String note = "note_1";
	    String sql = "insert into t_role(role_name, note) values(?, ?)";
	    return jdbcTemplate.update(sql, roleName, note);
	}

	/**
	 * 删除角色
	 * @param jdbcTemplate -- 模板
	 * @param id -- 角色编号，主键
	 * @return 影响条数
	 */
	public int deleteRole(JdbcTemplate jdbcTemplate, Long id) {
	    String sql = "delete from t_role where id=?";
	    return jdbcTemplate.update(sql, id);
	}

	public int updateRole(JdbcTemplate jdbcTemplate, Role role) {
	    String sql = "update t_role set role_name=?, note = ? where id = ?";
	    return jdbcTemplate.update(sql, role.getRoleName(), role.getNote(), role.getId());
	}

	/**
	 * 查询角色列表
	 * @param jdbcTemplate--模板
	 * @param roleName --角色名称
	 * @return 角色列表
	 */
	public List<Role> findRole(JdbcTemplate jdbcTemplate, String roleName) {
	    String sql = "select id, role_name, note from t_role where role_name like concat('%',?, '%')";
	    Object[] params = {roleName};//组织参数
	    //使用RowMapper接口组织返回（使用lambda表达式）
	    List<Role> list = jdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) -> {
	        Role result = new Role();
	        result.setId(rs.getLong("id"));
	        result.setRoleName(rs.getString("role_name"));
	        result.setNote(rs.getString("note"));
	        return result;
	    });
	    return list;
	}
	
	/**
	 * 使用ConnectionCallback接口进行回调
	 * @param jdbcTemplate 模板
	 * @param id 角色编号
	 * @return 返回角色
	 */
	public Role getRoleByConnectionCallback(JdbcTemplate jdbcTemplate, Long id) {
		Role role = null;
	    //这里写成Java 8的Lambda表达式，如果你使用低版本的Java，需要使用ConnectionCallback匿名类
		role = jdbcTemplate.execute((Connection con) -> {
			Role result = null;
			String sql = "select id, role_name, note from t_role where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Role();
				result.setId(rs.getLong("id"));
				result.setNote(rs.getString("note"));
				result.setRoleName(rs.getString("role_name"));
			}
			return result;
		});
		return role;
	}

	/**
	 * 使用StatementCallback接口进行回调
	 * @param jdbcTemplate模板
	 * @param id角色编号
	 * @return返回角色
	 */
	public Role getRoleByStatementCallback(JdbcTemplate jdbcTemplate, Long id) {
		Role role = null;
	     //这里写成Java 8的lambda表达式，如果你使用低版本的Java，需要使用StatementCallback的匿名类
		role = jdbcTemplate.execute((Statement stmt) -> {
			Role result = null;
			String sql = "select id, role_name, note from t_role where id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result = new Role();
				result.setId(rs.getLong("id"));
				result.setNote(rs.getString("note"));
				result.setRoleName(rs.getString("role_name"));
			}
			return result;
		});
		return role;
	}
}
