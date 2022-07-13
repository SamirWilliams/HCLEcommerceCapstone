package com.usermanagement.dao;

import com.usermanagement.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDao {

	private Connection connection;
	//TODO Change to also include order price
	private static final String INSERT_ORDER = "INSERT INTO orders (userId, productId, orderPrice, quantity) " +
			"VALUES (?, ?, ?, ?);";

	public OrderDao(Connection connection) {
		this.connection = connection;
	}

	public Boolean insertOrder(Order order) {
		boolean result = false;

		try {

			PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_ORDER);
			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setInt(2, order.getProductId());
			preparedStatement.setDouble(3, order.getOrderPrice());
			preparedStatement.setInt(4, order.getQuantity());

			preparedStatement.executeUpdate();

			result = true;

		} catch (Exception e) {
			System.out.println("insertOrder Error");
			e.printStackTrace();
		}

		return result;
	}

}
