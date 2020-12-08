package oxakam.maven.db.maven_mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
	
public class UserDaoImplTest {
	
	private Connection conn;
	private List<User> users;
	private static final int NUM_TEST_USERS = 1000;
	
	private List<User> loadUsers() throws IOException {
		
		/** load text from a file and split into strings and turn those 
		    into user objects using strings as user names 
		
		   return Files:
			   .load lines of a file as a bunch of strings 
			   .split lines in arrays of strings
			   .bunch of lists [a,b,c,d]                         
			   .unpack data structures within a list into words
			   .filter words with length between 3 and 20 
			   .turn words into items of User object
			   .limit number of users 
			   .add to the list of users  */
		
		// @formatter:off
		
		return Files
			.lines(Paths.get("../greatexpectations.txt"))
			.map(line -> line.split("[^A-Za-z]"))		
			.map(Arrays::asList)
			.flatMap(List -> List.stream())
			.filter(word -> word.length() > 3 & word.length() < 20)
			.map(word -> new User(word))
			.limit(NUM_TEST_USERS)
			.collect(Collectors.toList());
		
		// @formatter:on
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		
		users = loadUsers();
		
		var props = Profile.getProperties("db");
		
		var db = Database.dbInstance();
		db.connect(props);
		
		conn = db.getConnection();
		conn.setAutoCommit(false); 	//all stmt. will execute within one transaction
	}
	
	@After
	public void tearDown() throws SQLException {
		
		Database.dbInstance().disconnect();		
	}
	
	@Test
	public void testSave() throws SQLException {
		
		User user = new User("Jupiter");
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		var stmt = conn.createStatement();
		
		var query = stmt.executeQuery("select id, name from user order by id desc");
		
		var result = query.next();
		
		assertTrue("Cannot retrieve inserted user", result);
		
		var name = query.getString("name");
		
		assertEquals("user name doesn't match retrieved", user.getName(), name);
	
		stmt.close();
		
		/* tested without commit to database:
		 * we saved user, retrieved it from db with the name we saved */
	}	
}
