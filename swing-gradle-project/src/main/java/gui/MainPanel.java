package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainPanel() {		
//		setBackground(Color.lightGray);

		var formLabel = new JLabel("Add user");
		formLabel.setFont(new Font("Verdana", Font.BOLD, 24));
	
		setLayout(new GridBagLayout());		//layout of main panel
		
		var gc = new GridBagConstraints();	
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.weightx = 1;			//distribute horizontal space		

		add(formLabel, gc);		//add form label to main panel	
		
		gc.gridy++;
		gc.anchor = GridBagConstraints.NORTH;
		
		add(createFormPanel(), gc);  //add nested panel to main panel	
	}	
	
	/* Create nested panel with controls */
	private JPanel createFormPanel() {		
		
		JPanel panel = new JPanel();
		
		var padding = 10;
		var etchedBorder = BorderFactory.createEtchedBorder();
		var emptyBorder  = BorderFactory.createEmptyBorder(padding, padding,
										 				   padding, padding);
		panel.setBorder(BorderFactory.createCompoundBorder(etchedBorder, 
														   emptyBorder));		
		panel.setLayout(new GridBagLayout());	
		
		var nameLabel = new JLabel("Name:");
		var passLabel = new JLabel("Password:");		
		var nameField = new JTextField(15);
		var passField = new JTextField(15);		
		var addButton = new JButton("Save");
	
		var gc = new GridBagConstraints();
				
		var rightPad = new Insets(0, 0, 0, 10);	 //space between label and field
		
		gc.gridy++;				//next row	
		gc.gridwidth = 1;
		gc.weighty = 0.8;
		gc.ipadx = 2;
		gc.ipady = 2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;	
		panel.add(nameLabel, gc);	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(nameField, gc);
		
		gc.gridy++;
		gc.weighty = 1.1;  
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		panel.add(passLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(passField, gc);
	
		gc.gridy++;
		gc.weighty = 18;	
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(addButton, gc);
		
		return panel;		
	}
	
}
