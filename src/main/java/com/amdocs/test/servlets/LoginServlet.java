package com.amdocs.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amdocs.test.beans.User;
import com.amdocs.test.db.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO log;

	public void init() {
		log = new UserDAO();
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String userid=request.getParameter("id");
		String pass=request.getParameter("password");
		
		User user = new User();
		user.setUser_id(userid);
		user.setPassword(pass);
		
		System.out.println(userid+" , "+pass);
		//LoginDAO log=new LoginDAO();
		//boolean status=log.login(user);
		
		if (log.logadmin(user)) {
			out.println("<p style='color:green;'>Welcome Admin.!</p>");
			 out.println("<meta http-equiv='refresh' content='2;URL=index.html'>");
			 User.admin = true;
		}else {
		
			if (log.login(user)) {
				//HttpSession session = request.getSession();
				// session.setAttribute("username",username);
				//response.sendRedirect("index.html");
				out.println("<p style='color:green;'>Welcome.!</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=index.html'>");
				System.out.println("Welcome");
				User.admin = false;
			} else {
				HttpSession session = request.getSession();
				System.out.println("User or password incorrect");
				//session.setAttribute("user", username);
				 out.println("<p style='color:red;'>User or password incorrect!</p>");
				 out.println("<meta http-equiv='refresh' content='2;URL=login.html'>");
				//response.sendRedirect("login.html");
			}
		
		}
		
	}

}
