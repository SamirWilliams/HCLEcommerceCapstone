package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.OrderDao;
import com.ecommercecapstone.model.Cart;
import com.ecommercecapstone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from cart.jsp
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(CheckOutServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			OrderDao orderDao = new OrderDao(DBCon.getConnection());
			//if cart_list and auth are not null will attempt to insert order into database
			if(cart_list != null && auth != null){
				boolean result = orderDao.insertOrder(cart_list, auth);
				//if order was successfully added to database will clear cart_list
				if (result){
					cart_list.clear();
				}
				response.sendRedirect("orders.jsp");
			} else {
				/*
				if cart_list is null and auth is not null will redirect to cart.jsp
				if cart_list is not null and auth is null will redirect to register.jsp
				 */
				if (auth == null) {
					response.sendRedirect("register.jsp");
				} else {
					response.sendRedirect("cart.jsp");
				}
			}
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}
}
