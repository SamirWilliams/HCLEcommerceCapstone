package com.ecommercecapstone.web;

import com.ecommercecapstone.connection.DBCon;
import com.ecommercecapstone.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

	final Logger logger = Logger.getLogger(AddProductServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter())
		{
			String name = request.getParameter("prodname");
			String image = request.getParameter("prodimage");
			String category = request.getParameter("prodcategory");
			double price = Double.parseDouble(request.getParameter("prodprice"));
			request.getSession().setAttribute("category", category);

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			if(image.isEmpty())
			{
				image = "Default.jpg";
			}

			boolean wasAdded = productDao.addProduct(name, image, price, category);
			
			if (wasAdded){
				request.getSession().setAttribute("wasAdded", true);
				response.sendRedirect("addproduct.jsp");
			}
		} catch (Exception e) {
			logger.log(Level.WARNING,(e.getMessage()));
		}
	}
}
