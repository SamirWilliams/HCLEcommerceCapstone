package com.usermanagement.dao;

import com.usermanagement.model.Cart;
import com.usermanagement.model.Order;
import com.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

	private Connection connection;
	//TODO Change to also include order price
	private static final String INSERT_INTO_ORDERS = "INSERT INTO orders (userId) VALUES (?);";
	private static final String SELECT_PREVIOUS_ORDER_ID = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 0,1;";
	private static final String INSERT_INTO_ORDER_DETAILS = "INSERT INTO order_details (orderId, productId, orderPrice, quantity) " +
			"VALUES (?, ?, ?, ?);";
	private static final String SELECT_ALL_ORDERS_BY_USER = "SELECT * from order_details " +
			"JOIN orders USING (orderId) JOIN products USING (productId) WHERE userId = ? ORDER BY orderId;";
	private static final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE orderId = ?;";

	public OrderDao(Connection connection) {
		this.connection = connection;
	}

	public List<Order> listUserOrders (int id){
		List<Order> orderList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_ORDERS_BY_USER);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setProductId(resultSet.getInt("productId"));
				order.setUserId(resultSet.getInt("userId"));
				order.setOrderPrice(resultSet.getDouble("orderPrice"));
				order.setQuantity(resultSet.getInt("quantity"));
				order.setOrderDate(resultSet.getString("orderDate"));
				order.setProductName(resultSet.getString("productName"));
				order.setProductImage(resultSet.getString("productImage"));
				order.setUnitPrice(resultSet.getDouble("unitPrice"));
				order.setCategory(resultSet.getString("category"));
				orderList.add(order);
			}

		} catch (Exception e) {
			System.out.println("listUsersOrders Error");
			e.printStackTrace();
		}
		return orderList;
	}

	public boolean cancelOrder(int orderId) {
		boolean result = false;
		try{
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_ORDER_BY_ID);
			preparedStatement.setInt(1, orderId);

			result = preparedStatement.executeUpdate() > 0;
		}catch (Exception e){
			System.out.println("cancelOrder Error");
			e.printStackTrace();
		}
		return result;
	}

	public Boolean insertOrder(List<Cart> cart_list, User auth) {
		boolean finalResult = false;
		boolean ordersResult = false;
		boolean orderDetailsResult = false;
		int orderId = 0;

		try {
			PreparedStatement insertOrder = this.connection.prepareStatement(INSERT_INTO_ORDERS);
			insertOrder.setInt(1, auth.getUserId());

			ordersResult = insertOrder.executeUpdate() > 0;

			PreparedStatement recentOrderId = this.connection.prepareStatement(SELECT_PREVIOUS_ORDER_ID);

			ResultSet resultSet = recentOrderId.executeQuery();

			while (resultSet.next()){
				orderId = resultSet.getInt("orderId");
			}

			if (orderId <= 0) {
				throw new SQLException("Failed to retrieve orderId from orders table");
			} else {
				for (Cart c: cart_list){
					Order order = new Order();
					order.setProductId(c.getProductId());
					order.setQuantity(c.getQuantity());
					order.setOrderPrice(c.getUnitPrice() * c.getQuantity());

					PreparedStatement insertOrderDetails = this.connection.prepareStatement(INSERT_INTO_ORDER_DETAILS);
					insertOrderDetails.setInt(1, orderId);
					insertOrderDetails.setInt(2, order.getProductId());
					insertOrderDetails.setDouble(3, order.getOrderPrice());
					insertOrderDetails.setInt(4, order.getQuantity());

					orderDetailsResult = insertOrderDetails.executeUpdate() > 0;
				}
			}
			if (ordersResult && orderDetailsResult){
				finalResult = true;
			}
		} catch (Exception e) {
			System.out.println("insertOrder Error");
			e.printStackTrace();
		}
		return finalResult;
	}

	public boolean insertOrder(Order order) {
		boolean finalResult = false;
		boolean ordersResult = false;
		boolean orderDetailsResult = false;
		int orderId = 0;

		try {
			PreparedStatement insertOrder = this.connection.prepareStatement(INSERT_INTO_ORDERS);
			insertOrder.setInt(1, order.getUserId());

			ordersResult = insertOrder.executeUpdate() > 0;

			PreparedStatement recentOrderId = this.connection.prepareStatement(SELECT_PREVIOUS_ORDER_ID);

			ResultSet resultSet = recentOrderId.executeQuery();

			while (resultSet.next()){
				orderId = resultSet.getInt("orderId");
			}

			if (orderId <= 0) {
				throw new SQLException("Failed to retrieve orderId from orders table");
			} else {
				PreparedStatement insertOrderDetails = this.connection.prepareStatement(INSERT_INTO_ORDER_DETAILS);
				insertOrderDetails.setInt(1, orderId);
				insertOrderDetails.setInt(2, order.getProductId());
				insertOrderDetails.setDouble(3, order.getOrderPrice());
				insertOrderDetails.setInt(4, order.getQuantity());

				orderDetailsResult = insertOrderDetails.executeUpdate() > 0;
			}
			if (ordersResult && orderDetailsResult){
				finalResult = true;
			}
		} catch (Exception e) {
			System.out.println("insertOrder Error");
			e.printStackTrace();
		}
		return finalResult;
	}
}
