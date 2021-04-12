package system.app;

import java.util.ArrayList;

public class PKV {
	private Employee loggedInAs;
	private ArrayList<Employee> employees;
	private ArrayList<Project> projects;
	private DateServer date;
	private int id = 0;

	public PKV() {
		this.employees = new ArrayList<Employee>();
		this.projects = new ArrayList<Project>();
		this.date = new DateServer();
	}


	public void add(Employee employee) {
		employees.add(employee);
		
	}


	public void login(Employee employee) {
		this.loggedInAs = employee;
		
	}


	public Employee getLoggedInAs() {
		return this.loggedInAs;
	}


	public void logOut() {
		this.loggedInAs = null;
		
	}


	public void createProject(String name) throws Exception {
		if (hasProject(name)) {
			throw new Exception("A project with that name already exsits");
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

}
