package com.javaweb.utils;
// tien ich
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionJDBCUtil {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static Connection getConnction() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return conn;
	}
}
