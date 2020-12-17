package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
	
public class UserDaoImplTest {
	
	private Connection conn;
	private List<User> users;
	private static final int NUM_TEST_USERS = 4;
	
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
			.map(word -> new User(word, "pass" + word))
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

	private int getMaxId() throws SQLException {
		
		var stmt = conn.createStatement();
		
		var res = stmt.executeQuery("select max(id) as mId from user");		
		res.next();
		
		var id = res.getInt("mId");
		
		stmt.close();
		
		return id;		
	}
	
	private List<User> getUsersInRange(int minID, int maxID) throws SQLException{
		
		List<User> retrieved = new ArrayList<User>();
		
		var stmt = conn.prepareStatement("select id, name, password from user where id >= ? and id <= ?");
		stmt.setInt(1, minID);
		stmt.setInt(2, maxID);
		
		var res = stmt.executeQuery();
		
		while(res.next()) {
			
			int id = res.getInt("id");
			String name = res.getString("name"); 
			String password = res.getString("password"); 
			
			var user = new User(id, name, password);			
			retrieved.add(user);
		}		
		stmt.close();
		
		return retrieved;		
	}
	
	@Test
	public void testFindAndUpdate() throws SQLException {
		
		var user = users.get(0);
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		//find
		var maxId = getMaxId();
		user.setId(maxId);
		
		var retrievedUserOpt = userDao.findById(maxId);
		
		assertTrue("No user retrieved", retrievedUserOpt.isPresent());
		
		var retrievedUser = retrievedUserOpt.get();	
		
		assertEquals("Retrieved users not match saved users", user, retrievedUser);		
		
		user.setName("Myname");			//update
		user.setPassword("pass123");
		
		userDao.update(user);
		
		retrievedUserOpt = userDao.findById(maxId);	
		
		assertTrue("No updated user retrieved", retrievedUserOpt.isPresent());
		
		retrievedUser = retrievedUserOpt.get();	
		
		assertEquals("Retrieved users not match updated users", user, retrievedUser);
		
		System.out.println("updated: " + retrievedUser);
	}
	
	@Test
	public void testSaveMultiple() throws SQLException {
		
		UserDao userDao = new UserDaoImpl();		
		for(var u: users) {
			userDao.save(u);
		}		
		var maxId = getMaxId();
		
		for(int i=0; i < users.size(); i++) {
			int id = (maxId - users.size()) + i + 1;
			
			users.get(i).setId(id);			
		}
		var retrievedUsers = getUsersInRange((maxId - users.size()) + 1, maxId);
		
		assertEquals("Size of retrieved users don't equal to number of test users", 
				      retrievedUsers.size(), NUM_TEST_USERS);
		
		assertEquals("Retrieved users not match saved users", users, retrievedUsers);
		
		System.out.println("testSaveMultiple - maxId: " + maxId);
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		UserDao userDao = new UserDaoImpl();		
		for(var u: users) {
			userDao.save(u);
		}		
		var maxId = getMaxId();
		
		for(int i=0; i<users.size(); i++) {
			int id = (maxId - users.size()) + i + 1;
			
			users.get(i).setId(id);			
		}
		
		var deleteUserIndex = NUM_TEST_USERS/2;
		var deleteUser = users.get(deleteUserIndex);
		
		users.remove(deleteUser);
		
		System.out.println("deleted: " + deleteUser);
		System.out.println("users:     " + users);
		
		userDao.delete(deleteUser);
		
		var retrievedUsers = getUsersInRange((maxId - NUM_TEST_USERS) + 1, maxId);

		System.out.println("retrieved: " + retrievedUsers);
		
		assertEquals("Size of retrieved users don't equal to number of test users", 
				      retrievedUsers.size(), users.size());
		
		assertEquals("Retrieved users not match saved users", users, retrievedUsers);
	}
	
	@Test
	public void testGetAll() throws SQLException {
		
		UserDao userDao = new UserDaoImpl();
		
		for(var u: users) {
			userDao.save(u);
		}
		
		var maxId = getMaxId();
		
		for(int i=0; i<users.size(); i++) {
			int id = (maxId - users.size()) + i + 1;
			
			users.get(i).setId(id);			
		}
		var dbUsers = userDao.getAll();
		
		dbUsers = dbUsers.subList(dbUsers.size() - users.size(), dbUsers.size());  //fromIndex, toIndex
		
		assertEquals("Size of retrieved users don't equal to number of test users", 
				      dbUsers.size(), NUM_TEST_USERS);
		
		assertEquals("Retrieved users not match saved users", users, dbUsers);
		
		System.out.println("testGetAll - maxId: " + maxId);
	}
	
	@Test
	public void testSave() throws SQLException {
		
		User user = new User("Jupiter", "jupiter123");
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		var stmt = conn.createStatement();
		
		var query = stmt.executeQuery("select id, name, password from user order by id desc");
		
		var result = query.next();
		
		assertTrue("Cannot retrieve inserted user", result);
		
		var name = query.getString("name");		
		assertEquals("user name doesn't match retrieved", user.getName(), name);

		var password = query.getString("password");		
		assertEquals("user name doesn't match retrieved", user.getPassword(), password);
	
		stmt.close();
		
		/* tested without commit to database:
		 * we saved user, retrieved it from db with the name we saved */
	}	
}
