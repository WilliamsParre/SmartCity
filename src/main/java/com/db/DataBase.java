package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	private static String DB_URL = "jdbc:mysql://localhost/tourx";
	private static String USER="root";
	private static  String PASS="MySQL";
	
	public static Connection getDBConnection() {
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(Exception ex) {
			System.out.println(ex);
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
