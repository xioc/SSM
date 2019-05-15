package com.learn.ssm.chapter4.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

//启用扫描注册的时候需要注解
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler implements TypeHandler<String> {

	Logger logger = Logger.getLogger(MyTypeHandler.class);

	@Override	
    public void setParameter(PreparedStatement ps, int i, String parameter,
			JdbcType jdbcType) throws SQLException {
		logger.info("设置string参数【" + parameter+"】");
		ps.setString(i, parameter);
	}

	@Override	
    public String getResult(ResultSet rs, String columnName)
			throws SQLException {
		String result = rs.getString(columnName);
		logger.info("读取string参数1【" + result+"】");
		return result;
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		String result = rs.getString(columnIndex);
		logger.info("读取string参数2【" + result+"】");
		return result;
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String result = cs.getString(columnIndex);
		logger.info("读取string参数3【" + result+"】");
		return result;
	}
}
