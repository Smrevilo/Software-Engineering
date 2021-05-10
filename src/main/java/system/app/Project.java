//Viktor s204166
package system.app;

import java.util.ArrayList;

public class Project {
	private String name;
	private int id;
	private ArrayList<Activity> activites;
	private Employee leader = null;
	private boolean isEditable = true;
	
	//Viktor s204166
	public Project(String name, int id) {
		this.name = name;
		this.id = id;
		activites = new ArrayList<Activity>();
	}
	
	//Viktor s204166
	public void createActivty(Employee employee, String name) throws Exception {
		if (hasActivity(name)) {
			throw new Exception("An activity with the name \"" + name + "\" already exsits");
		}
		if (leader != employee) {
			throw new Exception("Only the project leader can create an new activity");
		}
		if (!isEditable) {
			throw new Exception("This project can not be modified");
		}
		if (name.equals("")) {
			throw new Exception("The activity's name must be at least 1 charecter long");
		}
		activites.add(new Activity(name));
	}
	
	//Viktor s204166
	public void deleteActivity(String name) throws Exception {
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				if(a.getPomodoros().size() > 0) {
					throw new Exception("The activity have employees assigned and can not be deleted");
				} else {
					activites.remove(a);
					return;
				}
			}
		}
		throw new Exception(name +" activty does not exists");
	}
	
	//Viktor s204166
	public boolean hasActivity(String name) {
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				return true;
			}
		}
		return false;		
	}
	
	//Viktor s204166
	public Activity getActivity(String name) throws Exception {
		for (Activity a : activites) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		throw new Exception("An activity with that name doesn't exsits");
	}
	
	//Viktor s204166
	public String makeReport() {
		String report = "";
		for (Activity activity : activites) {
			report += "Activity " + activity.getName() + " " + activity.printTime() +  "\n";
			report += "Is the activity finished: " + activity.getStatusOfActivity() + "\n";
			report += "Hours registred so far: " + ((float)activity.getTotalTime())/2.0 + "\n";
			report += "Expected workload: " + activity.getWorkload() + "\n";
			String activityReport = activity.getReport();
			if (activityReport.equals("")) {
				report += "\tThis activity has no registred hours\n\n";
			} else {
				report += activityReport + "\n";
			}
		}
		if (report.equals("")) {
			report = "There is no activites";
		}
		return report;
	}
	
	//Viktor s204166
	public ArrayList<Activity> getActivities() {
		return activites;
	}
	
	//Viktor s204166
	public Employee getLeader() {
		return leader;
	}
	
	//Viktor s204166
	public void setLeader(Employee leader) {
		this.leader = leader;
	}
	
	//Viktor s204166
	public String getName() {
		return name;
	}
	
	//Viktor s204166
	public void setEditable(boolean editable) {
		isEditable = editable;
	}
	
	//Viktor s204166
	public boolean getEditable() {
		return isEditable;
	}
}
