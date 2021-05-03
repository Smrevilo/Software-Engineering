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
	
	public void createActivty(String name) throws Exception {
		if (hasActivity(name)) {
			throw new Exception("An activity with the name \"" + name + "\" already exsits");
		}
		activites.add(new Activity(name));
	}
	
	public boolean hasActivity(String name) {
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				return true;
			}
		}
		return false;		
	}
	
	public Activity getActivity(String name) throws Exception {
		if (!hasActivity(name)) {
			throw new Exception("An activity with that name doesn't exsits");
		}
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public String makeRepport() {
		String repport = "";
		for (Activity activity : activites) {
			repport += "Activity " + activity.getName() + "\n";
			String activityRepport = activity.getRepport();
			if (activityRepport.equals("")) {
				repport += "\tThis activity has no registred hours\n\n";
			} else {
				repport += "\t"+activityRepport + "\n";
			}
		}
		if (repport.equals("")) {
			repport = "There is no activites";
		}
		return repport;
	}
	
	public ArrayList<Activity> getActivities() {
		return activites;
	}
	
	public Employee getLeader() {
		return leader;
	}
	
	public void setLeader(Employee leader) {
		this.leader = leader;
	}
	
	public String getName() {
		return name;
	}

	
}
