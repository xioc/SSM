package com.learn.ssm.chapter4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SqlSessionFactoryUtils {

	private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

	private static SqlSessionFactory sqlSessionFactory = null;

	private SqlSessionFactoryUtils() {
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		synchronized (LOCK) {
			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			try {
				 inputStream = Resources.getResourceAsStream(resource);
				 sqlSessionFactory = new
				 SqlSessionFactoryBuilder().build(inputStream);
				
				//采用程序传递加密参数,启用它之前，修改用户密码为密文，可参考main方法......
//				InputStream in = Resources.getResourceAsStream("jdbc.properties");
//				Properties props = new Properties();
//				props.load(in);
//				String username = props.getProperty("database.username");
//				String password = props.getProperty("database.password");
//				// 解密用户和密码，并在属性中重置
//				props.put("database.username", CodeUtils.decode(username));
//				props.put("database.password", CodeUtils.decode(password));
//				inputStream = Resources.getResourceAsStream(resource);
//				// 使用程序传递的方式覆盖原有的properties属性参数
//				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, props);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return sqlSessionFactory;
		}
	}

	public static SqlSession openSqlSession() {
		if (sqlSessionFactory == null) {
			getSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}

	public static void main(String[] args) {
		System.out.println(CodeUtils.encode("root"));
		System.out.println(CodeUtils.encode("123456"));
	}

	static class CodeUtils {
		private static String encode(String src) {
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(src.getBytes());
		}

		private static String decode(String des) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				return new String(decoder.decodeBuffer(des));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}