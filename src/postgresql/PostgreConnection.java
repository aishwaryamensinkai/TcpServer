package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreConnection {
	private final String url = "jdbc:postgresql://127.0.0.1:5432/onlineShoppingdb";
	private final String user = "postgres";
	private final String password = "password";
	
	 public Connection connect() {
	        Connection conn = null;
	        try {
	            conn = (Connection) DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully.");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }

	        return conn;
	    }
}
