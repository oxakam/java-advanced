package creating.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int [] ids = {0, 1, 2, 3};
		String [] names = {"John", "Mary", "Kim","Harry"};
		
		
		/* load the driver class for SQLite */
		Class.forName("org.sqlite.JDBC");
		
		/* driver to enable java API for working with SQL (kind of JDBC API to connect to DB) 
		   with URL to tell java where to find database */
		
		String dbURL = "jdbc:sqlite:people.db";
		
		var conn = DriverManager.getConnection(dbURL);
		
		var statm = conn.createStatement();		
		
		conn.setAutoCommit(false);	//turn Autocommit off for not to update database immediately		
		
		/* create table 'user' first time with columns: 
		   primary key 'id' of integer type and 'name' of text type */	
		
		var sql = "create table if not exists user (id integer primary key, name text not null)";		
		statm.execute(sql);			//create table	

		/* insert data into user table with statement values */
		
		sql = "insert into user (id, name) values (?, ?)";
		
		/* prepared statements (array) */
		
		var insertStatm = conn.prepareStatement(sql);
		
		for (int i = 0; i < ids.length; i++) {
			
			insertStatm.setInt(1, ids[i]);
			insertStatm.setString(2, names[i]);
			
//			insertStatm.executeUpdate();	//call this execute for first insert only
		}		
		conn.commit();	/** use commit to save data permanently without execute/executeUpdate statement 
						/*  if you run a hole group of statements 
						/*  since we use setAutoCommit(false) */
		
		insertStatm.close();		
		
		/* queries */
		
		sql = "select id, name from user";
		var res = statm.executeQuery(sql);	
		
		while (res.next()) {
			int id = res.getInt("id");
			String name = res.getString("name");
			
			System.out.println(id + ": " + name);
		}		
		
		sql = "drop table user";		
//		statm.execute(sql);			//delete table
		
		statm.close();		
		conn.close();
		
	}

}
