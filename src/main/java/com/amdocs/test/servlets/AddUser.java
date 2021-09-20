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

import com.amdocs.test.beans.User;
import com.amdocs.test.db.UserDAO;
import com.amdocs.test.utilities.DBUtil;

@WebServlet("/register")
public class AddUser extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userdb;

	public void init() {
		userdb = new UserDAO();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
			
		String name = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		String password = request.getParameter("password");
		String photo = request.getParameter("photo");
		String c_id = request.getParameter("c_id");
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
		
		User user = new User();
		user.setUsername(name);
		user.setPhone_no(phone);
		user.setEmail(email);
		user.setAddress(address);
		user.setReg_date(currentTime);
		user.setPassword(password);
		user.setUpload_photo(photo);
		user.setCourse_id(c_id);
		

	
		if (userdb.add(user)) {
			out.println("<p style='color:green;'>User added succesfully.!</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
			
		}else {
			HttpSession session = request.getSession();
			System.out.println("Error, user was not saved");
			 out.println("<p style='color:red;'>Error, user was not saved</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=register.html'>");
				}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//System.out.println("This is get method");
PrintWriter out = response.getWriter();
		
		Connection conn = DBUtil.getConnection();
		
		
		
		String sql = "select * from user2";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>USER ID</th><th>NAME</th><th>PHONE</th><th>EMAIL</th><th>ADDRESS</th><th>REGDATE</th><th>PHOTO</th><th>ENROLLED COURSE</th></tr>");

			while(rs.next()){
					out.println("<tr><td><center>"+rs.getString("user_id")+"</center></td>"
		               + "<td><center>"+rs.getString("name")+"</center></td>"
		               + "<td><center>"+rs.getString("phone_no")+"</center> "
		               		+ "<td><center>"+rs.getString("email")+"</center> "
		               		+ "<td><center>"+rs.getString("address")+"</center> "
		               		+ "<td><center>"+rs.getString("reg_date")+"</center> "
		               		+ "<td><center>"+rs.getString("upload_photo")+"</center> "
		               		+ "<td><center>"+rs.getString("course_id")+"</center> "
		               		+ "</td></tr>");
				}
				out.println("</table>");
		}catch (SQLException e){
			out.println("Connection Error!");
			e.printStackTrace();
		}
	}
}
