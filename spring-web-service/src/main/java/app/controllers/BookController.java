package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Book;

@RestController
public class BookController {

	@GetMapping("/books")		//localhost:8080/books
	public Book books() {
		
		var book = new Book("Charles Dickens", "Great Expectations");
		
		return book;
	}
}
