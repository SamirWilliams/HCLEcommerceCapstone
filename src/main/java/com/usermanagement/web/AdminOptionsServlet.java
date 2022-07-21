package com.usermanagement.web;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.usermanagement.connection.DBCon;
import com.usermanagement.dao.*;

@WebServlet("/")
public class AdminOptionsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;
	private UserDao userDao;
	
	/*
	 * Initialization.
	 */
	public void init() throws ServletException 
	{
		productDao = new ProductDao(DBCon.getConnection());
		userDao = new UserDao(DBCon.getConnection());	
	}
	
	
	/*
	 * HTTP GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	
	/*
	 * HTTP POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getServletPath();
		
		switch(action)
		{
			case "/add-product":
			try 
			{
				add(request, response);
			} 
			catch (SQLException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
				break;
				
			case "/update-product":
				try 
				{
					update(request, response);
				} 
				catch (SQLException | ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
					break;
				
			case "/delete-product":
				try 
				{
					delete(request, response);
				} 
				catch (SQLException | ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
					break;
				
				default:
			response.sendRedirect("index.jsp");
				break;
		}
	}
	
	
	/*
	 * Add new product.
	 */
	private void add(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ClassNotFoundException, ServletException
	{
		try(PrintWriter out = res.getWriter())
		{
			String name = req.getParameter("prodname");
			String image = req.getParameter("prodimage");
			String category = req.getParameter("prodcategory");
			double price = Double.parseDouble(req.getParameter("prodprice"));
			
			if(image.isEmpty())
			{
				image = "Default.jpg";
			}
			
			productDao.addProduct(name, image, price, category);
			
			req.getRequestDispatcher("index.jsp").include(req, res);
			out.println("<h4 style = 'color: red; text-align: center'> Product Posted. </h4>");
		}	
	}
	
	
	/*
	 * Update product.
	 */
	private void update(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ClassNotFoundException, ServletException
	{
		try(PrintWriter out = res.getWriter())
		{
			int id = Integer.parseInt(req.getParameter("prodID"));
			String name = req.getParameter("prodname");
			String image = req.getParameter("prodimage");
			double price = Double.parseDouble(req.getParameter("prodprice"));
			String category = req.getParameter("prodcategory");
			
			if(image.isEmpty())
			{
				image = productDao.getSingleProduct(id).getProductImage();
			}

			productDao.updateProduct(id, name, image, price, category);
			req.getRequestDispatcher("index.jsp").include(req, res);
			out.println("<h4 style = 'color: red; text-align: center'> Product Updated. </h4>");
		}	
	}
	
	
	/*
	 * Delete product.
	 */
	private void delete(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ClassNotFoundException, ServletException
	{
		try(PrintWriter out = res.getWriter())
		{
			int id = Integer.parseInt(req.getParameter("prodID"));
			
			productDao.deleteProduct(id);
			
			req.getRequestDispatcher("index.jsp").include(req, res);
			out.println("<h4 style = 'color: red; text-align: center'> Product Deleted. </h4>");
		}	
	}
}