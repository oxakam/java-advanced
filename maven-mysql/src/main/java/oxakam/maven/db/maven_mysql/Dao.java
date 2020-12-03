/**
 *  DAO interface (Data Access Object)
 */
package oxakam.maven.db.maven_mysql;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	void save(T t);	
	void update(T t);
	void delete(T t);
	
	Optional<T> findById(int id);
	
	List<T> getAll();
	
}
