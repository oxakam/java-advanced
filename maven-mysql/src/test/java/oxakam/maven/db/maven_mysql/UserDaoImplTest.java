package oxakam.maven.db.maven_mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
	
public class UserDaoImplTest {
	
	private Connection conn;
	
	@Before
	public void setUp() throws SQLException {
		
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
