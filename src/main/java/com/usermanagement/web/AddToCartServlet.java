package com.usermanagement.web;

import com.usermanagement.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			List<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			String category = (String) request.getSession().getAttribute("category");

			Cart cart = new Cart();
			cart.setProductId(id);
			cart.setCategory(category);
			cart.setQuantity(1);

			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			if (cart_list == null) {
				cartList.add(cart);
				request.getSession().setAttribute("addedToCart", true);
				request.getSession().setAttribute("cart-list", cartList);
				response.sendRedirect("product-list.jsp");
			} else {
				cartList = cart_list;
				boolean alreadyInCart = false;

				for (Cart c : cart_list) {
					if (c.getProductId() == id) {
						alreadyInCart = true;
						request.getSession().setAttribute("alreadyInCart", true);
						response.sendRedirect("product-list.jsp");
					}
				}
				if (!alreadyInCart) {
					cartList.add(cart);
					request.getSession().setAttribute("addedToCart", true);
					response.sendRedirect("product-list.jsp");
				}
			}
		}
	}
}
