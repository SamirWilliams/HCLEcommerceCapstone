package com.usermanagement.dao;

import com.usermanagement.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private static final String INSERT_USERS_SQL = "INSERT INTO user"
			+ "(firstName, lastName, phoneNumber, email ,address, city, zipcode, country) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_LOGIN_SQL = "INSERT INTO login"
			+ "(id, username, password) VALUES (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "SELECT firstName, lastName, phoneNumber, email, address, city" +
			", zipCode, country FROM user WHERE id = ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM user;";
	private static final String DELETE_USERS_SQL = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE_USERS_SQL = "UPDATE user " +
			"SET firstName = ?, lastName = ?, phoneNumber = ?, email = ?, address = ?, city = ?" +
			", zipCode = ?, country = ? WHERE id = ?";
	private final List<String> dbLoginInfo = new ArrayList<>();

	public UserDAO(){
		getLoginInfo();
	}

	private void getLoginInfo(){
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("src/main/resources/DBLoginInfo.JSON"));
			JSONObject jsonObject = (JSONObject) obj;
			dbLoginInfo.add(0,(String) jsonObject.get("url"));
			dbLoginInfo.add(1, (String) jsonObject.get("username"));
			dbLoginInfo.add(2, (String) jsonObject.get("password"));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbLoginInfo.get(0), dbLoginInfo.get(1), dbLoginInfo.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user){
		try (Connection connection = getConnection();
		        PreparedStatement usersStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			usersStatement.setString(1, user.getFirstName());
			usersStatement.setString(2, user.getLastName());
			usersStatement.setString(3, user.getPhoneNumber());
			usersStatement.setString(4, user.getEmail());
			usersStatement.setString(5, user.getAddress());
			usersStatement.setString(6, user.getCity());
			usersStatement.setString(7, user.getZipCode());
			usersStatement.setString(8, user.getCountry());
			usersStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String zipCode = rs.getString("zipCode");
				String country = rs.getString("country");
				user = new User(id, firstName, lastName, phoneNumber, email, address, city, zipCode, country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String zipCode = rs.getString("zipCode");
				String country = rs.getString("country");
				users.add(new User(id, firstName, lastName, phoneNumber, email, address, city, zipCode, country));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
		        PreparedStatement usersStatement = connection.prepareStatement(DELETE_USERS_SQL)){
			usersStatement.setInt(1, id);
			rowDeleted = usersStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	//TODO
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
		        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPhoneNumber());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getCity());
			statement.setString(7, user.getZipCode());
			statement.setString(8, user.getCountry());
			statement.setInt(9, user.getUserID());

			rowUpdated = statement.executeUpdate() > 0;
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
