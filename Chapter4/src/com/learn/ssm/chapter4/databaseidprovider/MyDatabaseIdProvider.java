package com.learn.ssm.chapter4.databaseidprovider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.log4j.Logger;

public class MyDatabaseIdProvider implements DatabaseIdProvider {

	private static final String DATEBASE_TYPE_DB2 = "DB2";
	private static final String DATEBASE_TYPE_MYSQL = "MySQL";
	private static final String DATEBASE_TYPE_ORACLE = "Oralce";

	private Logger log = Logger.getLogger(MyDatabaseIdProvider.class);

	@Override
	public void setProperties(Properties props) {
		log.info(props);
	}

	@Override
	public String getDatabaseId(DataSource dataSource) throws SQLException {
		Connection connection = dataSource.getConnection();
		String dbProductName = connection.getMetaData().getDatabaseProductName();
		if (MyDatabaseIdProvider.DATEBASE_TYPE_DB2.equals(dbProductName)) {
			return "db2";
		} else if (MyDatabaseIdProvider.DATEBASE_TYPE_MYSQL
				.equals(dbProductName)) {
			return "mysql";
		} else if (MyDatabaseIdProvider.DATEBASE_TYPE_ORACLE
				.equals(dbProductName)) {
			return "oracle";
		} else {
			return null;
		}
	}

}
