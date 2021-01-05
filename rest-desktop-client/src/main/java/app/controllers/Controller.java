package app.controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.gui.CreateBookPanel;
import app.gui.MainFrame;
import app.gui.ViewBooksPanel;
import app.model.Book;
import app.services.BookService;

public class Controller {
	
	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private ViewBooksPanel viewPanel;
	private BookService bookService;
	
	private final List<Book> bookList = new ArrayList<Book>();
	
	public Controller() {
		
		bookService = new BookService();
		
		viewPanel = new ViewBooksPanel(bookList);
	
//		try {
//			var bookList = bookService.getAll();
//			viewPanel = new ViewBooksPanel(bookList);
//			
//		} catch (IOException e) {
////			e.printStackTrace();
//			System.out.println("Server connection error - ViewBooksPanel is not shown"); 
//		}
		
		createPanel = new CreateBookPanel();
		
		createPanel.setFormListener((author, title) -> {
			try {
				bookService.save(new Book(author, title));
				refresh();
				
			} catch (IOException e) {
//				e.printStackTrace();
				JOptionPane.showMessageDialog(mainFrame, 
						"Error saving book", "Book service not available",
						JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(author + ": " + title);
		});
		
		mainFrame = new MainFrame(createPanel, viewPanel);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				refresh();
			}
		});
	}
	
	protected void refresh() {
		
		bookList.clear();
		
		try {
			bookList.addAll(bookService.getAll());
			
			viewPanel.refresh();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainFrame, 
					"Error refreshing book list", 
					"Book service not available",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}