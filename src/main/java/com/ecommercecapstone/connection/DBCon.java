package com.ecommercecapstone.connection;

import com.ecommercecapstone.web.AddProductServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCon {

	private static Connection connection = null;
	private static String url = "jdbc:mysql://ecomdb.mysql.database.azure.com:3306/ecommercecapstone";
	//Change these to log in to your database
	private static String username = "swilliams";
	private static String identification = "Sherida22";

	static final Logger logger = Logger.getLogger(DBCon.class.getName());

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//getLoginInfo();
				connection = DriverManager.getConnection(url, username, identification);
			} catch (Exception e) {
				logger.log(Level.WARNING,(e.getMessage()));
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
