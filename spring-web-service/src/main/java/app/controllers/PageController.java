package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")			//corresponds to Home page
	public String home() {
		return "index";
	}
	
	@GetMapping("/about")		//localhost:8080/about
	public String about() {
		return "about";
	}
}
