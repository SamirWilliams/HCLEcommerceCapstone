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

@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("UpdateProductServlet doGet Error");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter())
		{
			int id = Integer.parseInt(request.getParameter("prodID"));
			String name = request.getParameter("prodname");
			String image = request.getParameter("prodimage");
			double price = Double.parseDouble(request.getParameter("prodprice"));
			String category = request.getParameter("prodcategory");
			request.getSession().setAttribute("category", category);

			ProductDao productDao = new ProductDao(DBCon.getConnection());

			if(image.isEmpty())
			{
				image = productDao.getSingleProduct(id).getProductImage();
			}

			boolean wasUpdated = productDao.updateProduct(id, name, image, price, category);
			
			if (wasUpdated){
				request.getSession().setAttribute("wasUpdated", true);
				response.sendRedirect("updateproduct.jsp");
			}
		} catch (Exception e) {
			System.out.println("UpdateProductServlet doPost Error");
			e.printStackTrace();
		}
	}

}
