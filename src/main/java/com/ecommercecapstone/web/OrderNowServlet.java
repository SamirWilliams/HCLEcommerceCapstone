package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.OrderDao;
import com.ecommercecapstone.model.Cart;
import com.ecommercecapstone.model.Order;
import com.ecommercecapstone.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Called from product-list.jsp and cart.jsp
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			User auth = (User) request.getSession().getAttribute("auth");
			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (auth != null){
				String productId = request.getParameter("id");
				double orderPrice = Double.parseDouble(request.getParameter("price"));
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));

				if (productQuantity <= 0){
					productQuantity = 1;
				}

				Order order = new Order();
				order.setUserId(auth.getUserId());
				order.setProductId(Integer.parseInt(productId));
				order.setOrderPrice(orderPrice * productQuantity);
				order.setQuantity(productQuantity);

				OrderDao orderDao = new OrderDao(DBCon.getConnection());
				boolean result = orderDao.insertOrder(order);

				/*
				If insertOrder() was successful and cart_list is not null then it removes only the selected
					product from the cart list
				 */

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
					System.out.println("Order Failed");
					response.sendRedirect("orders.jsp");
				}
			} else {
				response.sendRedirect("register.jsp");
			}
		} catch (Exception e){
			System.out.println("OrderNowServlet doGet Error");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e){
			System.out.println("OrderNowServlet doPost Error");
			e.printStackTrace();
		}
	}
}
