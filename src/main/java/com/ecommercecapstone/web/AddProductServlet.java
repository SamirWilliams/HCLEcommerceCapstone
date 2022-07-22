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

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

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

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			if(image.isEmpty())
			{
				image = "Default.jpg";
			}

			productDao.addProduct(name, image, price, category);

			request.getRequestDispatcher("index.jsp").include(request, response);
			out.println("<h4 style = 'color: red; text-align: center'> Product Posted. </h4>");
		}
	}
}
