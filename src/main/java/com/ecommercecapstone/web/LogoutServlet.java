package com.ecommercecapstone.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Called from navbar.jsp
@WebServlet("/user-logout")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			if (request.getSession().getAttribute("auth") != null) {
				request.getSession().removeAttribute("auth");
				request.getSession().removeAttribute("cart-list");
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

