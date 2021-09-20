package com.amdocs.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/deleteCourse")
public class DeleteCourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		CourseDAO coursedb = new CourseDAO();
		
		String c_id = request.getParameter("id");
		
		Course course = new Course();
		course.setC_id(c_id);;
		
		String valStrUser=c_id.trim();
		System.out.println(valStrUser);
		if (valStrUser!="" && valStrUser!=null) {
			if (coursedb.delete(course)) {
				out.println("<p style='color:green;'>Course deleted succesfully.!</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
				
			}else {
				HttpSession session = request.getSession();
				System.out.println("Error, course was not deleted");
				 out.println("<p style='color:red;'>Error, user was not deleted, verify ID</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
					}
			
		}else {
			 out.println("<p style='color:red;'>Please specify ID to delete</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=courses.html'>");
		}
		
		
				
}
}
