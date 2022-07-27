package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.ProductDao;
import com.ecommercecapstone.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Called from index.jsp
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(ProductServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			ProductDao productDao = new ProductDao(DBCon.getConnection());
			List<Product> productList = productDao.getAllProducts();

			String category = request.getParameter("cat");

			request.getSession().setAttribute("category", category);//Sets the category of products to show
			request.getSession().setAttribute("productList", productList);

			response.sendRedirect("product-list.jsp");
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}
}
