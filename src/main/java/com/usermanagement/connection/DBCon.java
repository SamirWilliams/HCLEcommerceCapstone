package com.usermanagement.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {

	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306/ecommercecapstone?useSSL=false";
	//TODO Change these
	private static String username = "root";
	private static String password = "password";

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//getLoginInfo();
				connection = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				System.out.println("getConnection Error");
				e.printStackTrace();
			}
		}
		return connection;
	}

	/*private static void getLoginInfo() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(String.valueOf(new File("src/main/resources/DBLoginInfo.JSON")));
			JSONObject jsonObject = (JSONObject) obj;
			url = (String) jsonObject.get("url");
			username = (String) jsonObject.get("username");
			password = (String) jsonObject.get("password");
		} catch (Exception e){
			System.out.println("getLoginInfo Error");
			e.printStackTrace();
		}
	}*/
}
