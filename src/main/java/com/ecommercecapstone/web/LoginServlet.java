package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.UserDao;
import com.ecommercecapstone.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from login.jsp
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(LoginServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			logger.log(Level.WARNING, "LoginServlet doGet error: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");

			UserDao userDAO = new UserDao(DBCon.getConnection());
			User user = userDAO.userLogin(email, password);

			if (user != null) { //if userLogin() was successful adds user to auth session attribute
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			} else { //if userLogin() failed redirects user to login.jsp with error message
				request.setAttribute("loginError", "Invalid Username or Password");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.include(request, response);
			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "LoginServlet doPost error: " + e.getMessage());
		}
	}
}
