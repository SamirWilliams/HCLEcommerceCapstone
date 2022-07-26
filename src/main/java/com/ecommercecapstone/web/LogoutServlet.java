package com.ecommercecapstone.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from navbar.jsp
@WebServlet("/user-logout")
public class LogoutServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(LogoutServlet.class.getName());
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
			logger.log(Level.WARNING, "LogoutServlet error: " + e.getMessage());
		}
	}

}

