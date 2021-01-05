package app.controllers;

import java.io.IOException;

import app.gui.CreateBookPanel;
import app.gui.MainFrame;
import app.gui.ViewBooksPanel;
import app.services.BookService;

public class Controller {
	
	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private ViewBooksPanel viewPanel;
	private BookService bookService;
	
	public Controller() {
		
		bookService = new BookService();
		
		try {
			var bookList = bookService.getAll();
			viewPanel = new ViewBooksPanel(bookList);
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Server connection error - ViewBooksPanel is not shown"); 
		}
		
		createPanel = new CreateBookPanel();
		
		createPanel.setFormListener((author, title) -> {
			System.out.println(author + ": " + title);
		});

		
		mainFrame = new MainFrame(createPanel, viewPanel);
	}

}