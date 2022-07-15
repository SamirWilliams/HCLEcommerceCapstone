package com.usermanagement.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-logout")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			if (request.getSession().getAttribute("auth") != null) {
				request.getSession().removeAttribute("auth");
				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e){
			System.out.println("LogoutServlet Error");
			e.printStackTrace();
		}
	}

}
