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
			String category = request.getParameter("cat");
			Cart cart = new Cart();
			cart.setProductId(id);
			cart.setCategory(category);
			cart.setQuantity(1);
			List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (cart_list == null) {
				cartList.add(cart);
				request.getSession().setAttribute("cart-list", cartList);
				response.sendRedirect("products.jsp?cat="+cart.getCategory());
			} else {
				cartList = cart_list;

				boolean exist = false;
				for (Cart c : cart_list) {
					if (c.getProductId() == id) {
						exist = true;
						request.setAttribute("exist", true);
						request.getRequestDispatcher("products.jsp").forward(request,response);
						//out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>Go to Shopping Cart</a></h3>");
					}
				}

				if (!exist) {
					cartList.add(cart);
					response.sendRedirect("products.jsp?cat="+cart.getCategory());
				}
			}
		}
	}
}
