package com.amdocs.test.db;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amdocs.test.beans.User;
import com.amdocs.test.utilities.DBUtil;

public class UserDAO {
	
	public boolean login(User user) {
		
		boolean status = false;
		Connection conn= DBUtil.getConnection();
		String sql = "SELECT * from user2 WHERE user_id=? and password=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			//System.out.println("Ke onda: +"+status+". user, pass"+user.getUser_id()+" , "+user.getPassword());
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return status;
	}
	
public boolean logadmin(User user) {
		
		boolean status = false;
		Connection conn= DBUtil.getConnection();
		String sql = "SELECT * from admin WHERE admin_id=? and password=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			//System.out.println("Ke onda: +"+status+". user, pass"+user.getUser_id()+" , "+user.getPassword());
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return status;
	}
	
public boolean add(User user) {
		
	boolean status=false;
	Connection conn= DBUtil.getConnection();
	
	String sql = "insert into user2(name,phone_no,email,address,reg_date,password,upload_photo,course_id) values(?,?,?,?,?,?,?,?)";
	try {
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPhone_no());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getAddress());
		ps.setString(5, user.getReg_date());
		ps.setString(6, user.getPassword());
		ps.setString(7, user.getUpload_photo());
		ps.setString(8, user.getCourse_id());
		
		ps.executeUpdate(); //insert, update,delete
		status=true;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return status;
	}

public boolean delete(User user) {
	
	
	
	boolean status=false;
	Connection conn= DBUtil.getConnection();
	
	String user_id = user.getUser_id();

	String sql = "DELETE FROM user2 WHERE user_id=?";
	try {
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, user_id);
		
		
		ps.executeUpdate(); //insert, update,delete
		status=true;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return status;
}
		
}
