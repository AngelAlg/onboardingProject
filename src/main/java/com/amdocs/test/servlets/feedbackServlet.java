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
import com.amdocs.test.utilities.*;

@WebServlet("/feedback")
public class feedbackServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		
		
		String user_id = request.getParameter("id");
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String fb = request.getParameter("fb");
		

		Connection conn = DBUtil.getConnection();
		if(user_id!="") {
			//System.out.println("Yeah, not null");
			String sql = "insert into feedback(user_id,name,email,feedback) values(?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user_id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, fb);
				
				
				ps.executeUpdate(); //insert, update,delete
				out.println("Data Stored Successfully!");
			} catch (SQLException e) {
				out.println("Connection Error!");
				e.printStackTrace();
			}
		}else {
			System.out.println("Please enter valid ID numbers");
		}
			
		
		
		
		
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	PrintWriter out = response.getWriter();
		
	Connection conn = DBUtil.getConnection();
		
		
		
		String sql = "SELECT feedback.*, user2.name from feedback INNER JOIN user2 ON feedback.user_id=user2.user_id;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>USER ID</th><th>NAME</th><th>EMAIL</th><th>FEEDBACK ID</th><th>FEEDBACK</th><th>USER NAME</th></tr>");

			while(rs.next()){
					out.println("<tr><td><center>"+rs.getString("user_id")+"</center></td>"
		               + "<td><center>"+rs.getString("name")+"</center></td>"
		               + "<td><center>"+rs.getString("Email")+"</center> "
		               		+ "<td><center>"+rs.getString("f_id")+"</center> "
		               		+ "<td><center>"+rs.getString("feedback")+"</center> "
		               		+ "<td><center>"+rs.getString("user2.name")+"</center> "
		               		+ "</td></tr>");
				}
				out.println("</table>");
		}catch (SQLException e){
			out.println("Connection Error!");
			e.printStackTrace();
		}
	}
}
