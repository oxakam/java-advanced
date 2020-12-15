package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainPanel() {		
		setBackground(Color.lightGray);
		
		var rightPad = new Insets(0, 0, 0, 10);	//space between label and field
		
		var formLabel = new JLabel("Add user");
		formLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		
		var nameLabel = new JLabel("Name:");
		var passLabel = new JLabel("Password:");		
		var nameField = new JTextField(15);
		var passField = new JTextField(15);		
		var addButton = new JButton("Save");
		
		setLayout(new GridBagLayout());	
		var gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.weightx = 1;			//distribute horizontal space
		gc.weighty = 10;
		
		gc.gridwidth = 2;		//span for 2 columns
		add(formLabel, gc);
		
		gc.gridy++;				//next row	
		gc.gridwidth = 1;
		gc.weighty = 0.8;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;	
		add(nameLabel, gc);	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		gc.gridy++;
		gc.weighty = 1.1;  
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passField, gc);
	
		gc.gridy++;
		gc.weighty = 18;	
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(addButton, gc);
	}

}
