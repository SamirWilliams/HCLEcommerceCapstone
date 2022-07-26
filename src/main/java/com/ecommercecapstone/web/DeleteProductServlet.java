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
		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("DeleteProductServlet doGet error");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter())
		{
			int id = Integer.parseInt(request.getParameter("prodID"));
			String category = request.getParameter("prodcategory");
			request.getSession().setAttribute("category", category);

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			boolean wasDeleted = productDao.deleteProduct(id);
			
			if (wasDeleted){
				request.getSession().setAttribute("wasDeleted", true);
				response.sendRedirect("deleteproduct.jsp");
			}
			
		} catch (Exception e) {
			System.out.println("DeleteProductServlet doPost error");
			e.printStackTrace();
		}
	}

}
