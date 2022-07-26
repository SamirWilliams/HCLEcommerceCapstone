package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from users.jsp
@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(DeleteProductServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try{
			String userId = request.getParameter("id");
			UserDao userDAO = new UserDao(DBCon.getConnection());
			if (userId != null){
				/*
				if userId is 1 then it will redirect the admin back to list-users (ListUserServlet)
				A userId of 1 means that the admin is trying to delete the root admin account
				 */
				if (Integer.parseInt(userId) == 1){
					request.getSession().setAttribute("adminAccountDelete", true);
					response.sendRedirect("list-users");
				} else {
					boolean result = userDAO.deleteUser(Integer.parseInt(userId));
					if (!result){
						System.out.println("Delete User Failed");
					}
					//Will always redirect admin to list-users (ListUserServlet) regardless of success or failure
					response.sendRedirect("list-users");
				}
			} else {
				response.sendRedirect("list-users");
			}
		} catch (Exception e){
			logger.log(Level.WARNING, "DeleteUserServlet doPost error: " + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e){
			logger.log(Level.WARNING, "DeleteUserServlet doGet error: " + e.getMessage());
		}
	}
}
