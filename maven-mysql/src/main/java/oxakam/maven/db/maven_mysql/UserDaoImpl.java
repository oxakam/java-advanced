package oxakam.maven.db.maven_mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User u) {		
		var conn = Database.dbInstance().getConnection();
		try {
			var stmt = conn.prepareStatement("insert into user (name) values (?)");			
			stmt.setString(1, u.getName());		//setString for (?)			
			stmt.executeUpdate();			
			stmt.close();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(User u) {
		var conn = Database.dbInstance().getConnection();
		try {
			var stmt = conn.prepareStatement("update user set name=? where id=?");
			stmt.setString(1, u.getName());
			stmt.setInt(2, u.getId());
			stmt.executeUpdate();
//			stmt.close();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(User u) {		
		var conn = Database.dbInstance().getConnection();		
		try {
			var stmt = conn.prepareStatement("delete from user where id=?");
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}				
	}

	@Override
	public Optional<User> findById(int id) {
		
		var conn = Database.dbInstance().getConnection();
		try {
			var stmt = conn.prepareStatement("select name from user where id=?");	
			stmt.setInt(1, id);		
			var res = stmt.executeQuery();
			
			if (res.next()) {
				var name = res.getString("name");				
				User user = new User(id, name);
				
				return Optional.of(user);
			}		
			stmt.close();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return Optional.empty();
	}

	@Override
	public List<User> getAll() 
	{
		List<User> users = new ArrayList<>();
		
		var conn = Database.dbInstance().getConnection();
		try {
			var stmt = conn.createStatement();
			var res = stmt.executeQuery("select id, name from user order by id");
			
			while (res.next()) {
				var id = res.getInt("id");
				var name = res.getString("name");
				
				users.add(new User(id, name));
			}		
			stmt.close();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return users;
	}

}
