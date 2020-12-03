package oxakam.maven.db.maven_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Database db = new Database();
	private static final String dbUrl = "jdbc:mysql://localhost:3306/people?serverTimezone=UTC";
	private Connection conn;
	
	public static Database dbInstance() {
		return db;
	}
	
	private Database() {
		
	}
	
	public Connection getConnection() {
		return conn;
	}
    
    public void connect() throws SQLException {
    	conn = DriverManager.getConnection(dbUrl, "root", "password123");
    }

    public void disconnect() throws SQLException {
    	conn.close();
    }	

}
