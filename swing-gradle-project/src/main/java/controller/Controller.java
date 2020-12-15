package controller;

import gui.MainFrame;
import gui.MainPanel;

public class Controller {

	private MainFrame mainFrame;
	private MainPanel mainPanel;
	
	public Controller() {
		mainFrame = new MainFrame();
		mainPanel = new MainPanel();
		
		mainFrame.setContentPane(mainPanel);	//add panel to a frame		
		
	}
	
}
