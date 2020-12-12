package annotations;

public class App {

	public static void main(String[] args) {

		var user = new User(0L, "Venus"); 
		
		var repo = new Repository<User>();
		
		repo.save(user);		
	}
}
