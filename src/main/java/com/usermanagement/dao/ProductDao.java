package com.usermanagement.dao;

import com.usermanagement.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection connection;
	private static final String SELECT_ALL_PRODUCTS = "SElECT * FROM products;";

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

}
