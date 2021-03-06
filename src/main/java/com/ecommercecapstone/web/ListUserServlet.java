package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.UserDao;
import com.ecommercecapstone.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from navbar.jsp
@WebServlet("/list-users")
public class ListUserServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(ListUserServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			UserDao userDao = new UserDao(DBCon.getConnection());
			List<User> userList = userDao.selectAllUsers();
			request.getSession().setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}

}
