package oxakam.maven.db.maven_mysql;

import java.util.Properties;

public class Profile {
	
	public static Properties getProperties(String name) {
		
		Properties props = new Properties();
    	
    	String env = System.getProperty("env");
    	
    	if(env == null) {
    		env = "dev";
    	}  			
    	String propFile = String.format("/config/%s.%s.properties", name, env);	
    	System.out.println(propFile);
    	
    	try {
			props.load(App.class.getResourceAsStream(propFile));	
			
		} catch (Exception e1) {
			throw new RuntimeException("Cannot load properties file: "+ propFile);
		} 
    	
    	return props;
	}

}
