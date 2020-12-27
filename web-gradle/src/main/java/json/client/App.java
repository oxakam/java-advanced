package json.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {

		var url = "http://localhost:8080/WebGradle/users";		
		try {
			var json = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
			
			Gson gson = new Gson();	
			var users = gson.fromJson(json, User[].class);
			
			System.out.println(json);
			
			for(var user : users) {
				System.out.println(user);
			}
			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
