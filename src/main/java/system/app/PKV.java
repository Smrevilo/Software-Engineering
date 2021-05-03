package system.app;

import java.util.ArrayList;
import java.util.Calendar;

public class PKV {
	private Employee loggedInAs;
	private ArrayList<Employee> employees;
	private ArrayList<Project> projects;
	private DateServer date;
	private int id = 0;
	private Project selectedProject;
	private Activity selectedActivity;
	private GUI gui;

	public Activity getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(Activity selectedActivity) {
		this.selectedActivity = selectedActivity;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public PKV() throws Exception {
		this.employees = new ArrayList<Employee>();
		this.projects = new ArrayList<Project>();
		this.date = new DateServer();
		EmployeeHelper temp = new EmployeeHelper();
		this.employees.addAll(temp.getEmployeeList());
		this.projects.addAll(temp.getProjectList());
		this.gui = new GUI(this);
	}

	public void add(Employee employee) {
		employees.add(employee);
	}

	public boolean login(String initials) {
		logOut();
		for (Employee employee : employees) {
			if (initials.equals(employee.getInitials())) {
				this.loggedInAs = employee;
				return true;
			}
		}
		return false;
	}

	public Employee getLoggedInAs() {
		return this.loggedInAs;
	}

	public void logOut() {
		this.loggedInAs = null;
	}

	public void createProject(String name) throws Exception {
		if (hasProject(name)) {
			throw new Exception("A project with the name \"" + name + "\" already exsits");
		}
		id++;
		Project newProject = new Project(name, this.date.getYear() * 10000 + id);
		projects.add(newProject);
	}

	public boolean hasProject(String name) {
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public Project getProject(String name) throws Exception {
		if (!hasProject(name)) {
			throw new Exception("A project with that name doesn't exsits");
		}
		for (Project p : projects) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	public void deleteProject(String name) throws Exception {
		if (!hasProject(name)) {
			throw new Exception("A project with that name doesn't exsits");
		}
		if (selectedProject.getLeader() != loggedInAs) {
			throw new Exception("Only the project leaders can delete the project");
		}
		Project project = getProject(name);
		projects.remove(project);
	}

	public String makeReportFor(String projectName) throws Exception {
		Project temp = getProject(projectName);
		if (temp.getLeader() == loggedInAs) {
			return "Project: " + projectName + "\n" + temp.makeRepport();
		} else {
			throw new Exception("Only the project leader can get a project report");
		}
	}
	
	public void setStartDateFor(String projectName, String activityName, int startDate, int startMonth, int startYear) throws Exception{		
		setSelectedProject(getProject(projectName));
		if (getSelectedProject().getLeader()==loggedInAs) {
			setSelectedActivity(getSelectedProject().getActivity(activityName));
			getSelectedActivity().setStartDate(startDate, startMonth, startYear);
		} else {
			throw new Exception("Only the project leader can set a startdate");
		}
	}
	
	public Calendar getStartDateFor(String projectName, String activityName) throws Exception{
		setSelectedProject(getProject(projectName));
		setSelectedActivity(getSelectedProject().getActivity(activityName));
		Calendar output= getSelectedActivity().getStartDate();
		return output;
	}

	public void setDeadlineFor(String projectName, String activityName, int deadDate, int deadMonth, int deadYear) throws Exception {
		setSelectedProject(getProject(projectName));
		if (getSelectedProject().getLeader()==loggedInAs) {
			setSelectedActivity(getSelectedProject().getActivity(activityName));
			getSelectedActivity().setDeadline(deadDate, deadMonth, deadYear);
		} else {
			throw new Exception("only the project leader can set a deadline");
		}
		
	}

	public Calendar getDeadlineFor(String projectName, String activityName) throws Exception {
		setSelectedProject(getProject(projectName));
		setSelectedActivity(getSelectedProject().getActivity(activityName));
		Calendar output= getSelectedActivity().getDeadline();
		return output;
	}

	public void startApp() {
		gui.start();
	}

}
