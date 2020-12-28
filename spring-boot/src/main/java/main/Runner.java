package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import greeters.Greeter;

@Component
public class Runner implements CommandLineRunner {
	
	@Autowired
	private Greeter greeter;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
		
		greeter.greet();		
	}
	
	@Bean
	public Greeter createGreeter() {
		return new Greeter();
	}
}