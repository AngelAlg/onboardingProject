package com.amdocs.test.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String dbUrl = "jdbc:mysql://localhost:3306/elearning";
	
	private static String username = "root";
	private static String password = "pass1";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("Connection Succesful");
			return conn;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}