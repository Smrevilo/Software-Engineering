package system.app;

import java.util.ArrayList;

public class Project {
	private String name;
	private int id;
	private ArrayList<Activity> activites;
	private Employee leader = null;
	
	public Project(String name, int id) {
		this.name = name;
		this.id = id;
		activites = new ArrayList<Activity>();
	}
	
	public String getName() {
		return name;
	}
	
	public void createActivty(String name) {
		activites.add(new Activity(name));
	}
	
	public boolean hasActivty(String name) {
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				return true;
			}
		}
		return false;		
	}
	
	public Employee getLeader() {
		return leader;
	}
	
	public void setLeader(Employee leader) {
		this.leader = leader;
	}
}
