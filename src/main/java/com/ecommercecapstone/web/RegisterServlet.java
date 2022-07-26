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

//Called from register.jsp and admin-register.jsp
@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("RegisterServlet doGet Error");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		UserDao userDao = new UserDao(DBCon.getConnection());
		int resultCode;
		try {
			String firstName = request.getParameter("register-fName");
			String lastName = request.getParameter("register-lName");
			String phoneNumber = request.getParameter("register-phoneNumber");
			String email = request.getParameter("register-email");
			String password = request.getParameter("register-password");
			String address = request.getParameter("register-address");
			String city = request.getParameter("register-city");
			String zipCode = request.getParameter("register-zipcode");
			String country = request.getParameter("register-country");
			String adminStatus = request.getParameter("register-adminStatus");
			int adminPage = Integer.parseInt(request.getParameter("admin-page"));
			//if adminPage is 0 then it m
			if (adminPage == 0) {
				resultCode = userDao.userRegister(firstName, lastName, phoneNumber, email, password, address, city, zipCode, country);
				if (resultCode == 1) { //resultCode being 1 means that the user was updated successfully
					//Add Email Stuff Here
					request.setAttribute("registerSuccess", "Your registration was successful!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
					requestDispatcher.include(request, response);
				} else if (resultCode == 0) { //resultCode being 0 means that something went wrong in the insertion process Check userDao userRegister();
					request.setAttribute("registerError", "Something went wrong, please make sure your information is correct");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
					requestDispatcher.include(request, response);
				} else if (resultCode == -1) { //resultCode being -1 means that a duplicate field was trying to be added to the database
					request.setAttribute("registerError", "Phone Number, Email or Password already exist in system");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
					requestDispatcher.include(request, response);
				} else { //added in case resultCode is ever not 1, 0, or -1
					response.sendRedirect("index.jsp");
				}
			} else {
				resultCode = userDao.adminUserRegister(firstName, lastName, phoneNumber, email, password, address, city, zipCode, country, Integer.parseInt(adminStatus));
				if (resultCode == 1) { //resultCode being 1 means that the user was updated successfully
					request.setAttribute("registerSuccess", "User registration was successful!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("list-users");
					requestDispatcher.include(request, response);
				} else if (resultCode == 0) { //resultCode being 0 means that something went wrong in the insertion process Check userDao adminUserRegister();
					request.setAttribute("registerError", "Something went wrong, please make sure user information is correct");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
					requestDispatcher.include(request, response);
				} else if (resultCode == -1) { //resultCode being -1 means that a duplicate field was trying to be added to the database
					request.setAttribute("registerError", "Phone Number, Email or Password already exist in system");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
					requestDispatcher.include(request, response);
				} else { //added in case resultCode is ever not 1, 0, or -1
					response.sendRedirect("index.jsp");
				}
			}
		} catch (Exception e) {
			System.out.println("RegisterServlet doPost Error");
			e.printStackTrace();
		}
	}
}
