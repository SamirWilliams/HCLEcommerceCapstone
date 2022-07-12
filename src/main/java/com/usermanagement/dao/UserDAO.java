package com.usermanagement.dao;

import com.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private Connection connection;

	private static final String INSERT_USERS_SQL = "INSERT INTO users"
			+ "(firstName, lastName, phoneNumber, address, city, zipcode, country, email) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE userId = ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
	private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE userId = ?;";
	private static final String UPDATE_USERS_SQL = "UPDATE users " +
			"SET firstName = ?, lastName = ?, phoneNumber = ?, email = ?, address = ?, city = ?" +
			", zipCode = ?, country = ? WHERE userId = ?;";
	private static final String USER_LOGIN_SQL = "SELECT * FROM users WHERE email = ? and password = SHA2(?, 512);";

	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(USER_LOGIN_SQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String zipCode = resultSet.getString("zipCode");
				String country = resultSet.getString("country");
				String retrievedEmail = resultSet.getString("email");
				user = new User(id, firstName, lastName, phoneNumber, address, city, zipCode, country, retrievedEmail);
			}

		} catch (SQLException e) {
			System.out.println("userLogin Error");
			printSQLException(e);
		}
		return user;
	}

	public void insertUser(User user) {
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPhoneNumber());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getCity());
			preparedStatement.setString(6, user.getZipCode());
			preparedStatement.setString(7, user.getCountry());
			preparedStatement.setString(8, user.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(int id) {
		User user = null;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_USER_BY_ID);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String zipCode = resultSet.getString("zipCode");
				String country = resultSet.getString("country");
				String email = resultSet.getString("email");
				user = new User(id, firstName, lastName, phoneNumber, address, city, zipCode, country, email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_USERS);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String zipCode = resultSet.getString("zipCode");
				String country = resultSet.getString("country");
				String email = resultSet.getString("email");
				users.add(new User(id, firstName, lastName, phoneNumber, address, city, zipCode, country, email));
			}
		} catch (SQLException e) {
			System.out.println("selectAllUsers Error");
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted = false;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_USERS_SQL);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("deleteUser Error");
			printSQLException(e);
		}
		return rowDeleted;
	}

	//TODO
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated = false;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_USERS_SQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPhoneNumber());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, user.getCity());
			preparedStatement.setString(7, user.getZipCode());
			preparedStatement.setString(8, user.getCountry());
			preparedStatement.setInt(9, user.getUserId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("deleteUser Error");
			printSQLException(e);
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
