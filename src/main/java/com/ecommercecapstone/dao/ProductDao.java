package com.ecommercecapstone.dao;

import com.ecommercecapstone.model.Cart;
import com.ecommercecapstone.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection connection;

	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products;";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE productId = ?;";
	private static final String SELECT_PRODUCT_BY_PRICE = "SELECT unitPrice FROM products WHERE productId = ?;";
	private static final String ADD_PRODUCT = "INSERT INTO Products(productName, productImage, unitPrice, category) VALUES(?, ?, ?, ?);";
	private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE productId = ?;";
	private static final String UPDATE_PRODUCT = "UPDATE Products SET productName = ?, productImage = ?, unitPrice = ?, category = ? WHERE productId = ?;";
	private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM products WHERE productName = ?;";

	public ProductDao(Connection connection) {
		this.connection = connection;
	}

	/*
	Used in ProductServlet
	Returns a list of Products from the database
	 */
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_PRODUCTS);){


			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				String productImage = resultSet.getString("productImage");
				double unitPrice = resultSet.getDouble("unitPrice");
				String category = resultSet.getString("category");
				Product product = new Product(productId, productName, productImage, unitPrice, category);
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	/*
	Used directly in cart.jsp
	Returns a list of Cart rows with populated data from the database
	Uses the productId from cart items from the List<Cart> cartList object
	 */
	public List<Cart> getCartProducts(List<Cart> cartList) {
		List<Cart> products = new ArrayList<>();
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_BY_ID);
					preparedStatement.setInt(1, item.getProductId());

					ResultSet resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						Cart row = new Cart();
						row.setProductId(resultSet.getInt("productId"));
						row.setProductName(resultSet.getString("productName"));
						row.setUnitPrice(resultSet.getDouble("unitPrice") * item.getQuantity());
						row.setCategory(resultSet.getString("category"));
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/*
	Used directly in cart.jsp
	Returns the sum of all items within the given ArrayList<Cart>
	 */
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (!cartList.isEmpty()) {
				for (Cart item : cartList) {
					PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_BY_PRICE);
					preparedStatement.setInt(1, item.getProductId());

					ResultSet resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						sum += resultSet.getDouble("unitPrice") * item.getQuantity();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("getTotalCartPrice Error");
			e.printStackTrace();
		}

		return sum;
	}

	/*
	 * Adding a product to the app/website and database.
	 */
	public boolean addProduct(String name, String image, double price, String category)
	{
		boolean added;
		int status = 0;

		try(PreparedStatement preparedStatement = this.connection.prepareStatement(ADD_PRODUCT);)
		{

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, image);
			preparedStatement.setDouble(3, price);
			preparedStatement.setString(4, category);
			
			status = preparedStatement.executeUpdate();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		if(status == 1)
		{
			added = true;
		}
		
		else
		{
			added = false;
		}
		
		return added;
	}
	
	/*
	 * Updating a product on the app/website and database.
	 */
	public boolean updateProduct(int id, String name, String image, double price, String category)
	{
		boolean updated;
		int status = 0;
		
		try(PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_PRODUCT);)
		{
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, image);
			preparedStatement.setDouble(3, price);
			preparedStatement.setString(4, category);
			preparedStatement.setInt(5, id);
			
			status = preparedStatement.executeUpdate();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		if(status == 1)
		{
			updated = true;
		}
		
		else
		{
			updated = false;
		}
		
		return updated;
	}
	
	/*
	 * Deleting a product from the app/website and database.
	 */
	public boolean deleteProduct(int id)
	{
		boolean deleted;
		int status = 0;
		
		try
		{
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_PRODUCT);
			preparedStatement.setInt(1, id);
			
			status = preparedStatement.executeUpdate();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		if(status == 1)
		{
			deleted = true;
		}
		
		else
		{
			deleted = false;
		}
		
		return deleted;
	}
	
	/*
	 * Retrieve a single product.
	 */
	public Product getSingleProduct(int id)
	{
		 Product row = null;

	     try
	     {
	    	 PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_BY_ID);
	    	 preparedStatement.setInt(1, id);
	         ResultSet rs = preparedStatement.executeQuery();

	         while (rs.next())
	         {
	        	 row = new Product();
	             row.setProductId(rs.getInt("productId"));
	             row.setProductName(rs.getString("productName"));
	             row.setProductImage(rs.getString("productImage"));
	             row.setUnitPrice(rs.getDouble("unitPrice"));
	             row.setCategory(rs.getString("category"));
	        }
	    }

	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }

	    return row;
	}




	//Search-bar operation dao

	public Product getProductByName(String name) {
		Product row = null;

		try {


			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				row = new Product();
				row.setProductId(resultSet.getInt("productId"));
				row.setProductName(resultSet.getString("productName"));
				row.setCategory(resultSet.getString("category"));
				row.setUnitPrice(resultSet.getDouble("unitPrice"));
				row.setProductImage(resultSet.getString("productImage"));

			}
		}catch (Exception e ) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return row;
	}




















}