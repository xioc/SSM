package com.ssm.chapter12.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssm.chapter12.pojo.Role;

public class JdbcExample {
	
	public Role getRole(Long id) {
		Role role = null;
		// 声明JDBC变量
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chapter12", "root", "123456");
			// 预编译SQL
			ps = con.prepareStatement("select id, role_name, note from t_role where id = ?");
			// 设置参数
			ps.setLong(1, id);
			// 执行SQL
			rs = ps.executeQuery();
			// 组装结果集返回POJO
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getLong(1));
				role.setRoleName(rs.getString(2));
				role.setNote(rs.getString(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// 异常处理
			e.printStackTrace();
		} finally {
			// 关闭数据库连接资源
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return role;
	}
}
