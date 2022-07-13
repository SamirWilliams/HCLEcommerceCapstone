package com.usermanagement.web;

import com.usermanagement.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet ("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String action = request.getParameter("action");
			int productId = Integer.parseInt(request.getParameter("id"));

			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			if (action != null && productId >= 1){
				if (action.equals("inc")){
					for (Cart c : cart_list){
						if (c.getProductId() == productId){
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				if (action.equals("dec")){
					for (Cart c : cart_list){
						if (c.getProductId() == productId && c.getQuantity() > 1){
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
			} else {
				response.sendRedirect("cart.jsp");
			}
		}catch (Exception e) {
			System.out.println("QuantityIncDecServlet Error");
			e.printStackTrace();
		}
	}
}
