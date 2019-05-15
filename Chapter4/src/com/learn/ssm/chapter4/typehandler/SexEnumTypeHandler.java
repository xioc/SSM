package com.learn.ssm.chapter4.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.learn.ssm.chapter4.enumeration.SexEnum;

public class SexEnumTypeHandler implements TypeHandler<SexEnum> {

	@Override
	public void setParameter(PreparedStatement ps, int i, SexEnum parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());
	}

	@Override
	public SexEnum getResult(ResultSet rs, String columnName)
			throws SQLException {
		int id = rs.getInt(columnName);
		return SexEnum.getSexById(id);
	}

	@Override
   public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
		int id = rs.getInt(columnIndex);
		return SexEnum.getSexById(id);
	}

	@Override
	public SexEnum getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int id = cs.getInt(columnIndex);
		return SexEnum.getSexById(id);
	}

}
