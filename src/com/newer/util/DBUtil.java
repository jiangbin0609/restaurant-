package com.newer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * 连接数据库的工具类*/

public  class DBUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn = null;

	static {
		try {
			// 定义属性对象
			Properties prop = new Properties();
			InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("com/newer/util/jdbc.properties");
			prop.load(is);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			// 加载并注册驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 连接数据库并返回连接对象
	 */
	public static Connection getConnection() {
		try {
			// 连接数据库
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	/*
	 * 关闭数据库连接
	 */
	public static void closeAll(Connection conn, Statement st, ResultSet re) {
		// 判断是否为空
		if (re != null) {
			try {
				re.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
//	public static void main(String[] args) {
//		conn=DbUtil.getConnection();
//		System.out.println(conn);
//	}
}

