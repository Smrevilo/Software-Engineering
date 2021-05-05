package system.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUILogin extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public JTextField txtInitals;
	public JButton bEnter;
	public JLabel labelLogin;
	private PKV system;
	
	public GUILogin(PKV system) {
		this.system = system;
		getContentPane().setLayout(new BorderLayout());
		
		//region CREATE COMPONENTS
		 bEnter = new JButton("Enter");
		 bEnter.addActionListener(this);
		 bEnter.setMaximumSize(new Dimension(400, 30));
		 bEnter.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 //Tilføj txt komponent
		 Dimension txtfldsize = new Dimension(400, 30);
		 
		 txtInitals = new JTextField();
		 txtInitals.setMaximumSize(txtfldsize);
		 txtInitals.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 
		 //tilføj label komponenter
		 labelLogin = new JLabel("Login with initals: ");
		 labelLogin.setMaximumSize(new Dimension(400, 30));
		 labelLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		//endregion
		 
		//region ADd COMPONENTS TO PANELS
		 //Tilføj komponenter til center panel
		 JPanel centerPanel = new JPanel();
		 centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		 centerPanel.add(labelLogin);
		 centerPanel.add(txtInitals, BorderLayout.CENTER);
	     getContentPane().add(centerPanel, BorderLayout.CENTER);
		 
	    //Tilføj komponenter til bund panel
		 JPanel bottomPanel = new JPanel();
		 bottomPanel.setLayout(new BorderLayout());
	     bottomPanel.add(bEnter, BorderLayout.SOUTH);
	     getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	    //endregion
	     
	     display();
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bEnter) {
        	String initials = txtInitals.getText();
			boolean success = system.login(initials);
			if (success) {
				try {
					GUIProject userProjects = new GUIProject(system, system.getEmployee(initials));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				
			}
        	
            //System.exit(0);
        }
    }
	
	public void display() {
        this.setTitle("Login Title"); // Set title on window
        this.setSize(400, 200);     // Set size
        this.setResizable(false);    // Allow the window to be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);      // Make the window visible
	}
	
}
