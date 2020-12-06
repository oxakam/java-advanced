package oxakam.maven.db.maven_mysql;

import java.sql.SQLException;
import java.util.Properties;

public class App 
{
    public static void main( String[] args )
    {
    	Properties props = new Properties();
    	
    	String env = System.getProperty("env");
    	
    	if(env == null) {
    		env = "dev";
    	}  			
    	String propFile = String.format("/config/db.%s.properties", env);	//env files with DB props created under src/main/resources
    	System.out.println(propFile);
    	
    	try {
			props.load(App.class.getResourceAsStream(propFile));	
			
		} catch (Exception e1) {
			System.out.println("Cannot load properties file: "+ propFile);
			return;
		} 
    	
        var db = Database.dbInstance();
         
        try {
			db.connect(props);
			
		} catch (SQLException e) {
			System.out.println("Cannot connect to database");
			return;
		}
        
		System.out.println("Connected");
		
		UserDao userDao = new UserDaoImpl();
		
//		userDao.save(new User("Mars"));			//save names into table 'user'
//		userDao.save(new User("Mercury"));
//		userDao.save(new User("Neptune"));
		
		var allUsers = userDao.getAll();
		allUsers.forEach(System.out::println);
		
		var userOpt = userDao.findById(4);
		
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			System.out.println("Retrieved: " + user);
			user.setName("Snoopy");
			userDao.update(user);
		}
		else {
			System.out.println("No user found");
		}
		userDao.delete(new User(3, null));
			
        try {
			db.disconnect();
			
		} catch (SQLException e) {
			System.out.println("Cannot close database connection");		
		}
    } 
    
}