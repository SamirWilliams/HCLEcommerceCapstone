package com.usermanagement.web;

import com.usermanagement.dao.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/")
public class UserServlet extends HttpServlet {

	private UserDAO userDAO;

	public void init() {
		userDAO = new UserDAO();
	}

}
