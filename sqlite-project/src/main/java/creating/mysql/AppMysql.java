package creating.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AppMysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int [] ids = {0, 1, 2, 3};
		String [] names = {"John", "Mary", "Kim","Harry"};		
		
		/* load the driver class for MySQL */
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		String dbURL = "jdbc:mysql://localhost:3306/people?serverTimezone=UTC";
		
		var conn = DriverManager.getConnection(dbURL, "root", "password123");
		
		var statm = conn.createStatement();		
		
		conn.setAutoCommit(false);		

		var sql = "insert into user (id, name) values (?, ?)";

		var insertStatm = conn.prepareStatement(sql);
		
		for (int i = 0; i < ids.length; i++) {
			
			insertStatm.setInt(1, ids[i]);
			insertStatm.setString(2, names[i]);
			
//			insertStatm.executeUpdate();	//call this execute for first insert only
		}
		
		conn.commit();	
		
		insertStatm.close();		
	
		sql = "select id, name from user";
		var res = statm.executeQuery(sql);	
		
		while (res.next()) {
			int id = res.getInt("id");
			String name = res.getString("name");
			
			System.out.println(id + ": " + name);
		}		

		statm.close();		
		conn.close();		
	}
}
