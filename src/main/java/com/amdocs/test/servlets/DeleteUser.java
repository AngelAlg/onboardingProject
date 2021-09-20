package com.amdocs.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amdocs.test.beans.User;
import com.amdocs.test.db.UserDAO;
import com.amdocs.test.utilities.DBUtil;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet{
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		UserDAO userdb = new UserDAO();
		
		String user_id = request.getParameter("id");
		
		User user = new User();
		user.setUser_id(user_id);;
		
		String valStrUser=user_id.trim();
		System.out.println(valStrUser);
		if (valStrUser!="" && valStrUser!=null) {
			if (userdb.delete(user)) {
				out.println("<p style='color:green;'>User deleted succesfully.!</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
				
			}else {
				HttpSession session = request.getSession();
				System.out.println("Error, user was not saved");
				 out.println("<p style='color:red;'>Error, user was not deleted, verify ID</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
					}
			
		}else {
			 out.println("<p style='color:red;'>Please specify ID to delete</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
		}
		
		
				
}
}
