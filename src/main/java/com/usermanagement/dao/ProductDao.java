package com.usermanagement.dao;

import com.usermanagement.model.Cart;
import com.usermanagement.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection connection;
	private static final String SELECT_ALL_PRODUCTS = "SElECT * FROM products;";
	private static final String SELECT_PRODUCT_BY_ID = "SElECT * FROM products WHERE productID = ?;";
	private static final String SELECT_PRODUCT_BY_PRICE = "SELECT unitPrice FROM products WHERE productID = ?;";

	public ProductDao(Connection connection) {
		this.connection = connection;
	}

	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_PRODUCTS);

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
}
