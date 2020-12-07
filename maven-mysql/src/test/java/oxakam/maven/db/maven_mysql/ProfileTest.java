package oxakam.maven.db.maven_mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ProfileTest {
	
	@Test
	public void testLoadDBconfig() {
	
		var props = Profile.getProperties("db");		
		/* props should not be null */
		assertNotNull("Cannot load DB properties", props);
		
		var dbName = props.getProperty("database");
		/* test database name */
		assertEquals("dbName incorrect", "people_test", dbName);
		
		
	}

}
