package com.ecommercecapstone.web;

import com.ecommercecapstone.model.Cart;

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
@WebServlet ("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {

	Logger logger = Logger.getLogger(QuantityIncDecServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String action = request.getParameter("action");
			int productId = Integer.parseInt(request.getParameter("id"));

			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			//checks if action and productId exist
			if (action != null && productId >= 1){
				//if action equals "inc" will add 1 to the specific cart products quantity
				if (action.equals("inc")){
					for (Cart c : cart_list){
						if (c.getProductId() == productId){
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							break;

						}
					}
					response.sendRedirect("cart.jsp");
				}
				/*
				if action equals "dec" will remove 1 to the specific cart products quantity
				cannot go below 1 quantity
				 */
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
			logger.log(Level.WARNING, "QuantityIncDecServlet error: " + e.getMessage());
		}
	}
}
