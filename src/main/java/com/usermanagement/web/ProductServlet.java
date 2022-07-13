package com.usermanagement.web;

import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.ProductDao;
import com.usermanagement.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		ProductDao productDao = new ProductDao(DBCon.getConnection());
		List<Product> productList = productDao.getAllProducts();

		String category = request.getParameter("cat");

		request.getSession().setAttribute("category", category);
		request.getSession().setAttribute("productList", productList);

		response.sendRedirect("product-list.jsp");
	}

}
