package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import greeters.Greeter;
import entities.User;
import repositories.UserDao;

@Component
public class Runner implements CommandLineRunner {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Runner");
		
		var user1 = new User("Morpheus", "morpheus@example.com");
		var user = userDao.save(user1);
		
		System.out.println(user);
		
		userDao.findAll().forEach(u -> System.out.println("Find all: " + u));
		
		var retrievedUserOpt = userDao.findById(user.getId());
		
		if(retrievedUserOpt.isPresent()) {
			System.out.println("Find by ID: " + retrievedUserOpt.get());
		}
		
		/* Adding CRUD repository method */
		var users = userDao.findByName("Morpheus");
		
		users.forEach(u -> System.out.println("Find by name: " + u));		
	}

}