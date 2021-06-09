  
package kr.co.jboard1.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	private static DBConfig instance = new DBConfig();
	private DBConfig() {}
	
	public static DBConfig getInstance() {
		return instance;
	}
		
	private final String HOST = "jdbc:mysql://13.209.73.49:3306/siopmy";
	private final String USER = "briant";
	private final String PASS = "k2917h06";
	
	public Connection getConnection() throws Exception {
	
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}
}