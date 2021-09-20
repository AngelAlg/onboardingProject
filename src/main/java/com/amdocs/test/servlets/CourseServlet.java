package com.amdocs.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amdocs.test.beans.Course;
import com.amdocs.test.beans.User;
import com.amdocs.test.db.CourseDAO;
import com.amdocs.test.db.UserDAO;
import com.amdocs.test.utilities.DBUtil;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseDAO coursedb;

	public void init() {
		coursedb = new CourseDAO();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		boolean permission=User.isAdmin();
		if (permission) {
			String name = request.getParameter("coursename");
			String desc = request.getParameter("desc");
			String fees  = request.getParameter("fee");
			String resource = request.getParameter("resource");
			
			
			
			Course course = new Course();
			course.setCname(name);
			course.setCdesc(desc);
			course.setFees(fees);
			course.setCresource(resource);
			

		
			if (coursedb.add(course)) {
				out.println("<p style='color:green;'>Course added succesfully.!</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
				
			}else {
				HttpSession session = request.getSession();
				System.out.println("Error, course was not saved");
				 out.println("<p style='color:red;'>Error, user was not saved</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
					}
		}else {
			out.println("<p style='color:red;'>You need admin permissions for this action</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
		}
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//System.out.println("This is get method");
PrintWriter out = response.getWriter();
		
		Connection conn = DBUtil.getConnection();
		
		
		
		String sql = "select * from course";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>COURSE ID</th><th>NAME</th><th>DESCRIPTION</th><th>FEES</th><th>RESOURCE</th></tr>");

			while(rs.next()){
					out.println("<tr><td><center>"+rs.getString("course_id")+"</center></td>"
		               + "<td><center>"+rs.getString("c_name")+"</center></td>"
		               + "<td><center>"+rs.getString("c_desp")+"</center> "
		               		+ "<td><center>"+rs.getString("c_fees")+"</center> "
		               		+ "<td><center>"+rs.getString("c_resource")+"</center> "
		               		
		               		
		               		+ "</td></tr>");
				}
				out.println("</table>");
		}catch (SQLException e){
			out.println("Connection Error!");
			e.printStackTrace();
		}
	}
}
