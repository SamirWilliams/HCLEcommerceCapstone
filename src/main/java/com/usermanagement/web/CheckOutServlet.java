package com.usermanagement.web;

import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.OrderDao;
import com.usermanagement.model.Cart;
import com.usermanagement.model.Order;
import com.usermanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			OrderDao orderDao = new OrderDao(DBCon.getConnection());
			if(cart_list != null && auth != null){
				boolean result = orderDao.insertOrder(cart_list, auth);
				if (result){
					cart_list.clear();
				}
				response.sendRedirect("orders.jsp");
			} else {
				if (auth == null) {
					response.sendRedirect("login.jsp");
				} else {
					response.sendRedirect("cart.jsp");
				}
			}
		}catch (Exception e) {
			System.out.println("CheckOutServlet Error");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
