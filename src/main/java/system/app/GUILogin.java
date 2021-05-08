package system.app;

import javax.swing.*;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

public class GUILogin extends JFrame implements ActionListener{

	//Menubar
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem home;
	private JMenuItem logOut;
	private JMenuItem available;
	
	//Login page
	private JTextField initialsField;
	private JButton loginButton;
	private JLabel loginLabel;
	private JLabel errorLabel;
	
	//Main page
	private JButton[] bProjectList;
	private JButton miscButton;
	private JButton createProjectButton;
	private JButton searchProjectButton;
	private JButton editProjectButton;
	
	//Project overview
	private JButton createActivityButton;
	private JButton setProjectLeaderButton;
	private JButton makeReportButton;
	
	//Non gui
	private PKV pkv;
	private ArrayList<Project> projects;
	
	public GUILogin(PKV pkv) {
		this.pkv = pkv;

		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		
		menuBar.add(file);
		
		makeLogin();
		
		//REMOVE LATER!!!!!!!!!!!!!!!!!
		initialsField.setText("ABCD");
		loginButton.doClick();
	}
	
	private void makeLogin() {
		getContentPane().removeAll();
		this.setSize(400, 130);
		
		menuBar.remove(0);
		menuBar.revalidate();
		this.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		
		loginLabel = new JLabel("Login with initals: ", SwingConstants.CENTER);
		
		initialsField = new JTextField(10);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		
		errorLabel = new JLabel();
		
		panel.add(initialsField);
		panel.add(loginButton);

		getContentPane().add(loginLabel, BorderLayout.NORTH);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(errorLabel, BorderLayout.SOUTH);
		
		display();
	}
	
	private void makeFrontpage() {
		getContentPane().removeAll();
		this.setSize(500, 400);

		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		home = new JMenuItem("Home");
		available = new JMenuItem("Search for available");
		logOut = new JMenuItem("Log out");
		
		home.addActionListener(this);
		available.addActionListener(this);
		logOut.addActionListener(this);

		file.add(home);
		file.add(available);
		file.add(logOut);
		
		menuBar.add(file);

	    miscButton = new JButton("Misc");
	    miscButton.addActionListener(this);

	    searchProjectButton = new JButton("Search For Project");
	    searchProjectButton.addActionListener(this);

	    createProjectButton = new JButton("Create New Project");
	    createProjectButton.addActionListener(this);

	    editProjectButton = new JButton("Edit Project");
	    editProjectButton.addActionListener(this);
	    
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.add(searchProjectButton);
	    bottomPanel.add(createProjectButton);
	    bottomPanel.add(editProjectButton);
	    getContentPane().add(bottomPanel, BorderLayout.CENTER);
		
		display();
	}

	private void makeProjectOverview() {
		getContentPane().removeAll();
		this.setSize(500, 400);

		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		home = new JMenuItem("Home");
		available = new JMenuItem("Search for available");
		logOut = new JMenuItem("Log out");
		
		home.addActionListener(this);
		available.addActionListener(this);
		logOut.addActionListener(this);

		file.add(home);
		file.add(available);
		file.add(logOut);
		
		menuBar.add(file);

	    createActivityButton = new JButton("Create Activity");
	    createActivityButton.addActionListener(this);

	    setProjectLeaderButton = new JButton("Set Project Leader");
	    setProjectLeaderButton.addActionListener(this);

	    makeReportButton = new JButton("Make Report");
	    makeReportButton.addActionListener(this);
		
	    JLabel info;
	    
	    JPanel infoPanel = new JPanel();
	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
	    
		info = new JLabel("Current Project: " + pkv.getSelectedProject().getName());
	    infoPanel.add(info);
	    Employee leader = pkv.getSelectedProject().getLeader();
		info = new JLabel("Current Project Leader: " + (leader == null ? "None" : leader.getInitials()));
	    infoPanel.add(info);
	    
		getContentPane().add(infoPanel, BorderLayout.WEST);
	    
	    JPanel panel = new JPanel();
	    panel.add(createActivityButton);
	    panel.add(setProjectLeaderButton);
	    panel.add(makeReportButton);
	    getContentPane().add(panel, BorderLayout.SOUTH);
		
		display();
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
        	String initials = initialsField.getText();
			try {
				pkv.login(initials);
				makeFrontpage();
			} catch (Exception e1) {
				errorLabel.setText(e1.getMessage());
			}
        } else if (e.getSource() == logOut) {
        	pkv.logOut();
        	makeLogin();
        } else if (e.getSource() == home) {
        	if (pkv.getLoggedInAs() != null) {
            	makeFrontpage();
        	}
        } else if (e.getSource() == available) {
        	getDate();
        } else if (e.getSource() == createProjectButton) {
        	String projectName = JOptionPane.showInputDialog("Name of new project:");
        	if (projectName == null) {
        		return;
        	}
        	try {
        		pkv.createProject(projectName);
				JOptionPane.showMessageDialog(this, "Project created successfully");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
        } else if (e.getSource() == editProjectButton) {
        	String projectName = JOptionPane.showInputDialog("Select project to edit:");
        	if (projectName == null) {
        		return;
        	}
        	try {
        		pkv.setSelectedProject(pkv.getProject(projectName));
            	makeProjectOverview();
        	} catch (Exception e1) {
            	JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        } else if (e.getSource() == setProjectLeaderButton) {
        	pkv.getSelectedProject().setLeader(pkv.getLoggedInAs());
        	makeProjectOverview();
        } else if (e.getSource() == createActivityButton) {
        	String activityName = JOptionPane.showInputDialog("Name of new activity:");
        	if (activityName == null) {
        		return;
        	}
        	try {
        		pkv.getSelectedProject().createActivty(pkv.getLoggedInAs(), activityName);
        	} catch (Exception e1) {
            	JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        } else if (e.getSource() == makeReportButton) {
        	try {
				JOptionPane.showMessageDialog(this, pkv.makeReportFor(pkv.getSelectedProject().getName()));
			} catch (Exception e1) {
            	JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    }
	
	private void getDate() {
		JDialog dia = new JDialog(this, "Search");
    	
    	JPanel textPanel = new JPanel();
    	textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
    	
    	JPanel writePanel = new JPanel();
    	writePanel.setLayout(new BoxLayout(writePanel, BoxLayout.PAGE_AXIS));
    	
    	JTextField day = new JTextField();
    	JTextField month = new JTextField();
    	JTextField year = new JTextField();

    	JLabel info = new JLabel("Day: ");
    	textPanel.add(info);
    	textPanel.add(Box.createRigidArea(new Dimension(10, 5)));
    	info = new JLabel("Month: ");
    	textPanel.add(info);
    	textPanel.add(Box.createRigidArea(new Dimension(10, 5)));
    	info = new JLabel("Year: ");
    	textPanel.add(info);
    	
    	
    	writePanel.add(day);
    	writePanel.add(month);
    	writePanel.add(year);
    	
    	dia.add(textPanel, BorderLayout.WEST);
    	dia.add(writePanel, BorderLayout.CENTER);
    	
    	JPanel buttonPanel = new JPanel();
    	//buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
    	
    	JButton ok = new JButton("Ok");
    	JButton cancel = new JButton("Cancel");
    	ok.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dia.removeAll();
    	    	dia.setVisible(false);
    			try {
    				int intDay = Integer.parseInt(day.getText());
    				int intMonth = Integer.parseInt(month.getText());
    				int intYear = Integer.parseInt(year.getText());
        			handleDate(intDay, intMonth, intYear);
    			} catch (NumberFormatException e1) {
                	JOptionPane.showMessageDialog(dia, "Not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
    			} catch (Exception e1) {
                	JOptionPane.showMessageDialog(dia, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	});
    	cancel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dia.removeAll();
    	    	dia.setVisible(false);
    		}
    	});
    	
    	buttonPanel.add(ok, BorderLayout.WEST);
    	buttonPanel.add(cancel, BorderLayout.EAST);
    	
    	dia.add(buttonPanel, BorderLayout.SOUTH);
    	
    	dia.setSize(150, 140);
    	dia.setLocation(this.getX(), this.getY());
    	dia.setVisible(true);
	}

	private void handleDate(int day, int month, int year) throws Exception {
		pkv.checkValid(day, month, year);
		ArrayList<Employee> available = pkv.getAvailableEmployees(new GregorianCalendar(year, month, day));
		int numAvailable = available.size();
		String out = "On that day there " + (numAvailable == 1 ? "is" : "are") + " " + numAvailable + " available employee" + (numAvailable == 1 ? "" : "s") + ":\n";
		for (int i = 0; i < numAvailable; i++) {
			out += "Initials: " + available.get(i).getInitials()+"\n";
		}
		JOptionPane.showMessageDialog(this, out);
	}

	private void display() {
		
		this.setJMenuBar(menuBar);
		
		Dimension screenDimension = getToolkit().getScreenSize();
        this.setTitle("Software Huset A/S"); // Set title on window
        this.setResizable(false);    // Allow the window to be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);      // Make the window visible
        this.setLocation(screenDimension.width/2 - getSize().width/2, screenDimension.height/2 - getSize().height/2);
	}
	
}
