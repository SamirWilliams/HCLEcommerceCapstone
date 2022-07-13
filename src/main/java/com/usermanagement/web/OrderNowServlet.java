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

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()){
			User auth = (User) request.getSession().getAttribute("auth");
			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (auth != null){
				String productId = request.getParameter("id");
				double orderPrice = Double.parseDouble(request.getParameter("price"));
				System.out.println("Product ID:" + productId);
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				System.out.println("Product Quantity:" + productQuantity);
				if (productQuantity <= 0){
					productQuantity = 1;
				}

				Order orderModel = new Order();
				orderModel.setProductId(Integer.parseInt(productId));//This is productID in mySQL DB
				orderModel.setUserId(auth.getUserId());
				orderModel.setQuantity(productQuantity);
				orderModel.setOrderPrice(orderPrice * productQuantity);

				OrderDao orderDao = new OrderDao(DBCon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);

				if (result) {
					if (cart_list != null){
						for (Cart c : cart_list){
							if (c.getProductId() == Integer.parseInt(productId)){
								cart_list.remove(c);
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				} else {
					out.println("Order Failed");
				}

			} else {
				//TODO Change to register.jsp after finishing the UI for that
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e){
			System.out.println("OrderNowServlet Error");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
