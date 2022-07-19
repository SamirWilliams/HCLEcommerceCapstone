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

//Called from product-list.jsp
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			List<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			String category = (String) request.getSession().getAttribute("category");
			double price = Double.parseDouble(request.getParameter("price"));

			//Creates new Cart and populates with given data from product-list.jsp
			Cart cart = new Cart();
			cart.setProductId(id);
			cart.setCategory(category);
			cart.setUnitPrice(price);
			cart.setQuantity(1);

			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			//Adds item to cartList and creates session attribute cart-list
			if (cart_list == null) {
				cartList.add(cart);
				request.getSession().setAttribute("addedToCart", true);
				request.getSession().setAttribute("cart-list", cartList);
				response.sendRedirect("product-list.jsp");
			} else {
				cartList = cart_list;
				boolean alreadyInCart = false;
				//Checks to see if item already in cartList
				for (Cart c : cart_list) {
					if (c.getProductId() == id) {
						alreadyInCart = true;
						request.getSession().setAttribute("alreadyInCart", true);
						response.sendRedirect("product-list.jsp");
					}
				}
				//Adds item to cartList if not found in cart_list
				if (!alreadyInCart) {
					cartList.add(cart);
					request.getSession().setAttribute("addedToCart", true);
					response.sendRedirect("product-list.jsp");
				}
			}
		} catch (Exception e) {
			System.out.println("AddToCartServlet Error");
			e.printStackTrace();
		}
	}
}
