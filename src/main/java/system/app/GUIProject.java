package system.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIProject extends JFrame implements ActionListener {
	
	public JButton[] bProjectList;
	private JButton bCreateProject;
	private Employee user;
	private PKV system;
	private ArrayList<Project> projects;
	public JTextField txtCreateProject;
	
	public GUIProject(PKV system, Employee user) throws Exception {
		this.user = user;
		this.system = system;
		getContentPane().setLayout(new BorderLayout());
		
		projects = (ArrayList<Project>) system.getProjects();
		
		bProjectList = new JButton[projects.size()];
		
		for (int i = 0; i < projects.size(); i++) {
			bProjectList[i] = new JButton(projects.get(i).getName());
			bProjectList[i].addActionListener(this);
			bProjectList[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		for (int i = 0; i < projects.size(); i++) {
			centerPanel.add(bProjectList[i], BorderLayout.CENTER);
		}
	    getContentPane().add(centerPanel, BorderLayout.CENTER);
	    
	    txtCreateProject = new JTextField(40);

	    bCreateProject = new JButton("Create New Project");
	    bCreateProject.addActionListener(this);
	    
	    JPanel BottomPanel = new JPanel();
	    BottomPanel.setLayout(new BorderLayout());
	    BottomPanel.add(txtCreateProject, BorderLayout.CENTER);
	    BottomPanel.add(bCreateProject, BorderLayout.EAST);
	    getContentPane().add(BottomPanel, BorderLayout.SOUTH);
	    
		
		display();
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bProjectList[0]) {
        	try {
				GUIActivityEditor editor = new GUIActivityEditor(system, user, projects.get(0));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        
        if (e.getSource() == bCreateProject) {
        	try {
				system.createProject(txtCreateProject.getText());
				txtCreateProject.setText("");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
    }
	
	public void display() {
        this.setTitle("Project Title"); // Set title on window
        this.setSize(500, 500);     // Set size
        this.setResizable(false);    // Allow the window to be resized
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);      // Make the window visible
	}
}
