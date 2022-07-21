package com.usermanagement.web;

import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter())
		{
			int id = Integer.parseInt(request.getParameter("prodID"));
			String name = request.getParameter("prodname");
			String image = request.getParameter("prodimage");
			double price = Double.parseDouble(request.getParameter("prodprice"));
			String category = request.getParameter("prodcategory");

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			if(image.isEmpty())
			{
				image = productDao.getSingleProduct(id).getProductImage();
			}

			productDao.updateProduct(id, name, image, price, category);
			request.getRequestDispatcher("index.jsp").include(request, response);
			out.println("<h4 style = 'color: red; text-align: center'> Product Updated. </h4>");
		}
	}

}
