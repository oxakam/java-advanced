package gui;

import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		super("Swing frame demo");

		//frame properties
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		setJMenuBar(createMenu());
	}
	
	private JMenuBar createMenu() {
		
		var menuBar = new JMenuBar();		
		var fileMenu = new JMenu("File");		
		var exitItem = new JMenuItem("Exit");
		
		exitItem.addActionListener(e -> System.exit(0));
		
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		
		return menuBar;
	}
	
	
	
}
