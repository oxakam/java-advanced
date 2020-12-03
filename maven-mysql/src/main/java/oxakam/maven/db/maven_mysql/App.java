package oxakam.maven.db.maven_mysql;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
        var db = Database.dbInstance();
         
        try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("Cannot connect to database");
		}
        
		System.out.println("Connected");
		
		UserDao userDao = new UserDaoImpl();
		
//		userDao.save(new User("Mars"));			//save names into table 'user'
//		userDao.save(new User("Mercury"));
//		userDao.save(new User("Neptune"));
		
		var allUsers = userDao.getAll();
		allUsers.forEach(System.out::println);
		
		var userOpt = userDao.findById(4);
		
		if (userOpt.isPresent())
			System.out.println("Retrieved: " + userOpt.get());
		else
			System.out.println("No user found");	
			
        try {
			db.disconnect();
			
		} catch (SQLException e) {
			System.out.println("Cannot close database connection");		
		}
    } 
    
}