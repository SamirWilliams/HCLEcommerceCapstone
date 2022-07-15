package com.usermanagement.web;

import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.UserDao;
import com.usermanagement.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");

			UserDao userDAO = new UserDao(DBCon.getConnection());
			User user = userDAO.userLogin(email, password);

			if (user != null) {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("error", "Invalid Username or Password");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
				requestDispatcher.include(request, response);
			}

		} catch (Exception e) {
			System.out.println("LoginServlet Error");
			e.printStackTrace();
		}
	}
}
