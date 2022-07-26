package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.OrderDao;
import com.ecommercecapstone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Called from orders.jsp
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User auth = (User) request.getSession().getAttribute("auth");
			String orderId = request.getParameter("id");
			if (orderId != null && auth != null) {
				OrderDao orderDao = new OrderDao(DBCon.getConnection());
				boolean result = orderDao.cancelOrder(Integer.parseInt(orderId));
				if (!result){
					System.out.println("Cancel Order Failed");
				}
			}
			//Will always redirect user to orders.jsp regardless of success or failure
			response.sendRedirect("orders.jsp");
		} catch (Exception e){
			System.out.println("CancelOrderServlet doGet Error");
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e){
			System.out.println("CancelOrderServlet doPost Error");
			e.printStackTrace();
		}

	}
}
