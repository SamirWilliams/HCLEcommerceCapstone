package com.usermanagement.web;

import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.UserDao;
import com.usermanagement.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Called from navbar.jsp
@WebServlet("/list-users")
public class ListUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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
			System.out.println("ListUserServlet Error");
			e.printStackTrace();
		}
	}

}
