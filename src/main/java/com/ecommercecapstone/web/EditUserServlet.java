package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from admin-edit-user.jsp
@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet {

	Logger logger = Logger.getLogger(EditUserServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int resultCode;
		response.setContentType("text/html;charset=UTF-8");
		try{
			String userId = request.getParameter("userId");
			String firstName = request.getParameter("edit-fName");
			String lastName = request.getParameter("edit-lName");
			String phoneNumber = request.getParameter("edit-phoneNumber");
			String email = request.getParameter("edit-email");
			String address = request.getParameter("edit-address");
			String city = request.getParameter("edit-city");
			String zipCode = request.getParameter("edit-zipcode");
			String country = request.getParameter("edit-country");
			int adminStatus = Integer.parseInt(request.getParameter("edit-adminStatus"));
			UserDao userDAO = new UserDao(DBCon.getConnection());
			if (userId != null){
				/*
				if userId is 1 then it will redirect the admin back to list-users (ListUserServlet)
				A userId of 1 means that the admin is trying to delete the root admin account
				 */
				if (Integer.parseInt(userId) == 1) {
					request.getSession().setAttribute("adminAccountChange", true);
					response.sendRedirect("list-users");
				} else {
					resultCode = userDAO.updateUser(Integer.parseInt(userId), firstName, lastName, phoneNumber, email, address, city, zipCode, country, adminStatus);
					if (resultCode == 1){ //resultCode being 1 means that the user was updated successfully
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("list-users");
						requestDispatcher.include(request, response);
					} else if (resultCode == 0) { //resultCode being 0 means that something went wrong in the insertion process Check userDao updateUser();
						request.setAttribute("userEditError", "Something went wrong, please make sure your information is correct");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-edit-user.jsp?id=" + userId);
						requestDispatcher.include(request, response);
					} else if (resultCode == -1){ //resultCode being -1 means that a duplicate field was trying to be added to the database
						request.setAttribute("userEditError", "Phone Number or Email already exist in system");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-edit-user.jsp?id=" + userId);
						requestDispatcher.include(request, response);
					} else { //added in case resultCode is ever not 1, 0, or -1
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("list-users");
						requestDispatcher.include(request, response);
					}
				}
			} else {
				response.sendRedirect("list-users");
			}
		} catch (Exception e){
			logger.log(Level.WARNING, "EditUserServlet doPost error: " + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e){
			logger.log(Level.WARNING, "EditUserServlet doGet error: " + e.getMessage());
		}
	}

}
