package com.usermanagement.dao;

import com.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	private Connection connection;

	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE userId = ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
	private static final String DELETE_USER = "DELETE FROM users WHERE userId = ?;";
	private static final String UPDATE_USER = "UPDATE users " +
			"SET firstName = ?, lastName = ?, phoneNumber = ?, email = ?, address = ?, city = ?" +
			", zipCode = ?, country = ?, isAdmin = ? WHERE userId = ?;";
	private static final String USER_LOGIN = "SELECT * FROM users WHERE email = ? and password = SHA2(?, 512);";
	private static final String USER_REGISTER = "INSERT INTO users (firstName, lastName, phoneNumber, address, " +
			"city, zipCode, country, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SHA2(?, 512));";
	private static final String USER_REGISTER_INFO_VALIDATION = "SELECT * FROM users WHERE email = ? " +
			"OR password = SHA2(? , 512) OR phoneNumber = ?;";
	private static final String ADMIN_USER_REGISTER = "INSERT INTO users (firstName, lastName, phoneNumber, address, " +
			"city, zipCode, country, email, password, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SHA2(?, 512), ?);";
	private static final String ADMIN_USER_REGISTER_INFO_VALIDATION = "SELECT COUNT(*) AS total FROM users WHERE email = ? " +
			"OR phoneNumber = ? AND NOT userId = ?;";

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	/*
	Used in LoginServlet
	Returns User based on user input
	Returns Null User if user input couldn't be found in database
	 */
	public User userLogin(String email, String password) {
		User user = null;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(USER_LOGIN);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

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
				boolean isAdmin = resultSet.getInt("isAdmin") != 0;
				user = new User(id, firstName, lastName, phoneNumber, address, city, zipCode, country, retrievedEmail, isAdmin);
			}

		} catch (SQLException e) {
			System.out.println("userLogin Error");
			printSQLException(e);
		}
		return user;
	}

	/*
	Used in RegisterServlet
	Basic user registration
	Returns 1 if user inserted successfully
	Returns 0 if user failed to insert
	Returns -1 if user data already found in database
	 */
	public int userRegister(String firstName, String lastName , String phoneNumber, String email, String password,
	                            String address, String city, String zipCode, String country){
		int resultCode = 0;
		try {

			//Checks to see if email, password, or phone number are already in database
			PreparedStatement userInfoValidationStatement = this.connection.prepareStatement(USER_REGISTER_INFO_VALIDATION);
			userInfoValidationStatement.setString(1, email);
			userInfoValidationStatement.setString(2, password);
			userInfoValidationStatement.setString(3, phoneNumber);

			ResultSet userInfo = userInfoValidationStatement.executeQuery();

			//If something is found in userInfo returns resultCode -1
			if (userInfo.next()){
				resultCode = -1;
			} else {
				PreparedStatement insertUserStatement = this.connection.prepareStatement(USER_REGISTER);
				insertUserStatement.setString(1, firstName);
				insertUserStatement.setString(2, lastName);
				insertUserStatement.setString(3, phoneNumber);
				insertUserStatement.setString(4, address);
				insertUserStatement.setString(5, city);
				insertUserStatement.setString(6, zipCode);
				insertUserStatement.setString(7, country);
				insertUserStatement.setString(8, email);
				insertUserStatement.setString(9, password);

				boolean result = insertUserStatement.executeUpdate() > 0;
				if (result) {
					resultCode = 1;
				}

			}
		}catch (SQLException e) {
			System.out.println("userRegister Error");
			printSQLException(e);
		}

		return resultCode;
	}

	/*
	Used in RegisterServlet
	Admin user registration
	Returns 1 if user inserted successfully
	Returns 0 if user failed to insert
	Returns -1 if user data already found in database
	*/
	public int adminUserRegister(String firstName, String lastName , String phoneNumber, String email, String password,
	                        String address, String city, String zipCode, String country, int isAdmin){
		int resultCode = 0;
		try {

			//Checks to see if email, password, or phone number are already in database
			PreparedStatement userInfoValidationStatement = this.connection.prepareStatement(USER_REGISTER_INFO_VALIDATION);
			userInfoValidationStatement.setString(1, email);
			userInfoValidationStatement.setString(2, password);
			userInfoValidationStatement.setString(3, phoneNumber);

			ResultSet userInfo = userInfoValidationStatement.executeQuery();

			//If something is found in userInfo returns resultCode -1
			if (userInfo.next()){
				resultCode = -1;
			} else {
				PreparedStatement insertUserStatement = this.connection.prepareStatement(ADMIN_USER_REGISTER);
				insertUserStatement.setString(1, firstName);
				insertUserStatement.setString(2, lastName);
				insertUserStatement.setString(3, phoneNumber);
				insertUserStatement.setString(4, address);
				insertUserStatement.setString(5, city);
				insertUserStatement.setString(6, zipCode);
				insertUserStatement.setString(7, country);
				insertUserStatement.setString(8, email);
				insertUserStatement.setString(9, password);
				insertUserStatement.setInt(10, isAdmin);

				boolean result = insertUserStatement.executeUpdate() > 0;
				if (result) {
					resultCode = 1;
				}

			}
		}catch (SQLException e) {
			System.out.println("userRegister Error");
			printSQLException(e);
		}

		return resultCode;
	}

	/*
	Used in ListUserServlet
	Returns List of Users
	 */
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
				boolean isAdmin = resultSet.getInt("isAdmin") != 0;
				users.add(new User(id, firstName, lastName, phoneNumber, address, city, zipCode, country, email, isAdmin));
			}
		} catch (SQLException e) {
			System.out.println("selectAllUsers Error");
			printSQLException(e);
		}
		return users;
	}

	/*
	Used in DeleteUserServlet
	Returns true if user deleted successfully
	Returns false otherwise
	 */
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_USER);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("deleteUser Error");
			printSQLException(e);
		}
		return rowDeleted;
	}

	/*
	Used in EditUserServlet
	Returns 1 if user inserted successfully
	Returns 0 if user failed to insert
	Returns -1 if user data already found in database
	*/
	public int updateUser(int userId, String firstName, String lastName, String phoneNumber, String email,
	                          String address, String city, String zipCode, String country, int adminStatus) {
		boolean result;
		int resultCode = 0;
		try {


			//Checks to see if email or phone number are already in database ignores current users ID
			PreparedStatement userInfoValidationStatement = this.connection.prepareStatement(ADMIN_USER_REGISTER_INFO_VALIDATION);
			userInfoValidationStatement.setString(1, email);
			userInfoValidationStatement.setString(2, phoneNumber);
			userInfoValidationStatement.setInt(3, userId);

			ResultSet userInfo = userInfoValidationStatement.executeQuery();

			//If userInfo returns more than 1 row resultCode is -1
			while (userInfo.next()){
				if (userInfo.getInt("total") > 1){
					resultCode = -1;
				}
			}

			if (resultCode != -1){
				PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_USER);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, phoneNumber);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, address);
				preparedStatement.setString(6, city);
				preparedStatement.setString(7, zipCode);
				preparedStatement.setString(8, country);
				preparedStatement.setInt(9, adminStatus);
				preparedStatement.setInt(10, userId);

				result = preparedStatement.executeUpdate() > 0;
				if (result) {
					resultCode = 1;
				}
			}
		}
		catch (SQLException e) {
			System.out.println("updateUser Error");
			printSQLException(e);
		}
		return resultCode;
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
