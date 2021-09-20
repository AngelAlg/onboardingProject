package com.amdocs.test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amdocs.test.beans.Course;
import com.amdocs.test.beans.User;
import com.amdocs.test.utilities.DBUtil;

public class CourseDAO {

	public boolean add(Course course) {
		
		boolean status=false;
		Connection conn= DBUtil.getConnection();
		
		String sql = "insert into course(c_name,c_desp,c_fees,c_resource) values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, course.getCname());
			ps.setString(2, course.getCdesc());
			ps.setString(3, course.getFees());
			ps.setString(4, course.getCresource());
			
			
			
			ps.executeUpdate(); //insert, update,delete
			status=true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return status;
		}
	
	public boolean delete(Course course) {
		
		
		
		boolean status=false;
		Connection conn= DBUtil.getConnection();
		
		String user_id = course.getC_id();

		String sql = "DELETE FROM course WHERE course_id=?";
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

