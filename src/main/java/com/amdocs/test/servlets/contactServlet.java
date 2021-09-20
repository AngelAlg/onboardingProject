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


@WebServlet("/contact")

public class contactServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		//out.println("Hello, this is a cool servlet");
		
		String user_id = request.getParameter("id");
		String name = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String msg = request.getParameter("msg");
		

			
		Connection conn = DBUtil.getConnection();
		
		
		
		String sql = "insert into contact(user_id,name,email, phone_no, message) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, msg);
			
			
			ps.executeUpdate(); //insert, update,delete
			out.println("<p style='color:green;'>Data stored succesfully.!</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=contact.html'>");
		} catch (SQLException e) {
			out.println("<p style='color:red;'>Connection Error</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//System.out.println("This is get method");
PrintWriter out = response.getWriter();
		
		Connection conn = DBUtil.getConnection();
		
		
		
		String sql = "SELECT contact.*, user2.name from contact INNER JOIN user2 ON contact.user_id=user2.user_id;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>USER ID</th><th>NAME</th><th>EMAIL</th><th>PHONE NO</th><th>MESSAGE</th><th>CONTACT ID</th><th>USER NAME</th></tr>");

			while(rs.next()){
					out.println("<tr><td><center>"+rs.getString("user_id")+"</center></td>"
		               + "<td><center>"+rs.getString("name")+"</center></td>"
		               + "<td><center>"+rs.getString("Email")+"</center> "
		               		+ "<td><center>"+rs.getString("Phone_no")+"</center> "
		               		+ "<td><center>"+rs.getString("Message")+"</center> "
		               		+ "<td><center>"+rs.getString("contact_id")+"</center> "
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
