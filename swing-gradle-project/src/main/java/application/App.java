package application;

import javax.swing.SwingUtilities;

import controller.Controller;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(Controller::new); 	//invoke when new controller creates

	}

}
