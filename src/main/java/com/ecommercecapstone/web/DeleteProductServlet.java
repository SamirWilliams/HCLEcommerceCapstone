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

@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter())
		{
			int id = Integer.parseInt(request.getParameter("prodID"));

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			productDao.deleteProduct(id);

			request.getRequestDispatcher("index.jsp").include(request, response);
			out.println("<h4 style = 'color: red; text-align: center'> Product Deleted. </h4>");
		}
	}

}
