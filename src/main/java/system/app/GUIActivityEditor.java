package system.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIActivityEditor extends JFrame implements ActionListener{
private static final long serialVersionUID = 1L;
	
	private Employee user;
	private PKV system;
	private Project project;
	
	public JButton[] bActivityList;
	private ArrayList<Activity> listActivity;
	private Activity selectedActivty;
	
	public JLabel labelSelectedProject;
	
	public JLabel labelSetLeader;
	public JTextField txtSetLeader;
	
	public JLabel labelCreateActivty;
	public JTextField txtCreateActivty;
	
	public JLabel labelSelectedActivty;
	public JLabel labelSelectedAcitvtyStats;
	
	public JLabel labelAddEmployee;
	public JTextField txtAddEmployee;
	
	public JLabel labelSetStartdate;
	public JTextField txtSetStartdate;
	
	public JLabel labelSetDeadline;
	public JTextField txtSetDeadline;
	
	public JButton bUpdate;
	
	public GUIActivityEditor(PKV system, Employee user, Project project) throws Exception {
		this.user = user;
		this.system = system;
		this.project = project;
		listActivity = project.getActivities();
		getContentPane().setLayout(new BorderLayout());
		selectedActivty = listActivity.get(0);
		bActivityList = new JButton[listActivity.size()];
		
		Dimension txtfldsize = new Dimension(400, 30);
		Dimension btnsize = new Dimension(100, 30);
		
		bUpdate = new JButton("Update");
		bUpdate.addActionListener(this);
		bUpdate.setMaximumSize(btnsize);
		bUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		for (int i = 0; i < listActivity.size(); i++) {
			bActivityList[i] = new JButton(listActivity.get(i).getName());
			bActivityList[i].addActionListener(this);
			bActivityList[i].setMaximumSize(btnsize);
			bActivityList[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		 
		 labelSelectedProject = new JLabel("Working in project: " + project.getName());
		 labelSelectedProject.setMaximumSize(new Dimension(400, 30));
		 labelSelectedProject.setAlignmentX(Component.LEFT_ALIGNMENT);
		 labelSelectedProject.setFont(new Font("", Font.PLAIN, 16));
		 
		 labelSetLeader = new JLabel("Set Project leader");
		 labelSetLeader.setMaximumSize(new Dimension(400, 30));
		 labelSetLeader.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 txtSetLeader = new JTextField(project.getLeader().getInitials());
		 txtSetLeader.setMaximumSize(txtfldsize);
		 txtSetLeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 labelCreateActivty = new JLabel("Create New Activty: ");
		 labelCreateActivty.setMaximumSize(new Dimension(400, 30));
		 labelCreateActivty.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 txtCreateActivty = new JTextField();
		 txtCreateActivty.setMaximumSize(txtfldsize);
		 txtCreateActivty.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 
		 
		 
		 
		 
		 String startDate = selectedActivty.getStartDate().get(Calendar.YEAR)+"/"+selectedActivty.getStartDate().get(Calendar.MONTH)+"/"+selectedActivty.getStartDate().get(Calendar.DAY_OF_MONTH)+"";
		 String deadline = selectedActivty.getDeadline().get(Calendar.YEAR)+"/"+selectedActivty.getDeadline().get(Calendar.MONTH)+"/"+selectedActivty.getDeadline().get(Calendar.DAY_OF_MONTH)+"";
		 String report = new String(selectedActivty.getRepport());
		 
		 labelSelectedActivty = new JLabel("Selected Activty is: " + selectedActivty.getName());
		 labelSelectedActivty.setMaximumSize(new Dimension(400, 30));
		 labelSelectedActivty.setAlignmentX(Component.LEFT_ALIGNMENT);
		 labelSelectedActivty.setFont(new Font("", Font.PLAIN, 16));
		 
		 labelSelectedAcitvtyStats= new JLabel("Report: " + report + "");
		 labelSelectedAcitvtyStats.setMaximumSize(new Dimension(400, 30));
		 labelSelectedAcitvtyStats.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 labelAddEmployee = new JLabel("Add Employee to selected Activity");
		 labelAddEmployee.setMaximumSize(new Dimension(400, 30));
		 labelAddEmployee.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 txtAddEmployee = new JTextField();
		 txtAddEmployee.setMaximumSize(txtfldsize);
		 txtAddEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 labelSetStartdate = new JLabel("Edit Start Date for Activty: ");
		 labelSetStartdate.setMaximumSize(new Dimension(400, 30));
		 labelSetStartdate.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 txtSetStartdate = new JTextField(startDate);
		 txtSetStartdate.setMaximumSize(txtfldsize);
		 txtSetStartdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 labelSetDeadline = new JLabel("Edit Deadline for Activty: ");
		 labelSetDeadline.setMaximumSize(new Dimension(400, 30));
		 labelSetDeadline.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 txtSetDeadline = new JTextField(deadline);
		 txtSetDeadline.setMaximumSize(txtfldsize);
		 txtSetDeadline.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 
		 
		 
		 
		
		 JPanel centerPanel = new JPanel();
		 centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		 centerPanel.add(labelSelectedProject);
		 centerPanel.add(labelSetLeader);
		 centerPanel.add(txtSetLeader, BorderLayout.CENTER);
		 
		 centerPanel.add(labelCreateActivty);
		 centerPanel.add(txtCreateActivty, BorderLayout.CENTER);
		 
		 centerPanel.add(labelSelectedActivty);
		 centerPanel.add(labelSelectedAcitvtyStats);
		 
		 centerPanel.add(labelAddEmployee);
		 centerPanel.add(txtAddEmployee, BorderLayout.CENTER);
		 
		 centerPanel.add(labelSetStartdate);
		 centerPanel.add(txtSetStartdate, BorderLayout.CENTER);
		 
		 centerPanel.add(labelSetDeadline);
		 centerPanel.add(txtSetDeadline, BorderLayout.CENTER);
	     getContentPane().add(centerPanel, BorderLayout.CENTER);
		
	     
	     
	     
		JPanel LeftPanel = new JPanel();
		LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.PAGE_AXIS));
		LeftPanel.setBackground(Color.DARK_GRAY);
		for (int i = 0; i < listActivity.size(); i++) {
			LeftPanel.add(bActivityList[i], BorderLayout.CENTER);
		}
	    getContentPane().add(LeftPanel, BorderLayout.WEST);
	    
	    JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
	    bottomPanel.add(bUpdate, BorderLayout.SOUTH);
	    getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	    
		display();
	}
	
	public void update() {
		System.out.println("updating...");
		txtSetLeader.setText(project.getLeader().getInitials());
		labelSelectedActivty.setText("Selected Activty is: " + selectedActivty.getName());
		String startDate = selectedActivty.getStartDate().get(Calendar.YEAR)+"/"+selectedActivty.getStartDate().get(Calendar.MONTH)+"/"+selectedActivty.getStartDate().get(Calendar.DAY_OF_MONTH)+"";
		String deadline = selectedActivty.getDeadline().get(Calendar.YEAR)+"/"+selectedActivty.getDeadline().get(Calendar.MONTH)+"/"+selectedActivty.getDeadline().get(Calendar.DAY_OF_MONTH)+"";
		String report = new String(selectedActivty.getRepport());
		labelSelectedAcitvtyStats.setText(report);
		txtAddEmployee.setText("");
		txtSetStartdate.setText(startDate);
		txtSetDeadline.setText(deadline);
		System.out.println("update complete");
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//select new activty
		for (int i = 0; i < listActivity.size(); i++) {
			if (e.getSource() == bActivityList[i]) {
				selectedActivty = listActivity.get(i);
	        }
		}
        
		//update activty fields
        if (e.getSource() == bUpdate) {
        	try {
        		
        		//set project leader
        		String setLeader = txtSetLeader.getText();
        		project.setLeader(system.getEmployee(setLeader));
        		
        		String createActivty = txtCreateActivty.getText();
        		if (!createActivty.equals("")) {
        			project.createActivty(createActivty);
				}
        		
        		//edit deadline
//        		String deadline = txtSetDeadline.getText();
//            	Scanner scan = new Scanner(deadline);
//            	scan.useDelimiter("/");
//            	int deadYear = Integer.parseInt(scan.next(deadline));
//            	int deadMonth = Integer.parseInt(scan.next(deadline));
//            	int deadDay = Integer.parseInt(scan.next(deadline));
//				system.setDeadlineFor(project.getName(), selectedActivty.getName(), deadDay, deadMonth, deadYear);
//				
				//add empployee
				String addEmployee = txtAddEmployee.getText();
				if (!addEmployee.equals("")) {
					selectedActivty.addEmployee(system.getEmployee(addEmployee));
				}
				
				
			} catch (Exception e1) {
				//update failed
				e1.printStackTrace();
			}
        	
        }
        update();
    }
	
	public void display() {
        this.setTitle("Activty Editor Title"); // Set title on window
        this.setSize(500, 500);     // Set size
        this.setResizable(false);    // Allow the window to be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);      // Make the window visible
	}
}