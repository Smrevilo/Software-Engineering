package system.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PKV {
	private Employee loggedInAs;
	private ArrayList<Employee> employees;
	private ArrayList<Project> projects;
	private DateServer date;
	private int id = 0;
	private Project selectedProject;
	private Activity selectedActivity;
	private GUI gui;
	private int threshold;

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
	
	public List<Project> getProjectForEmployee(Employee user) throws Exception {
		System.out.println("create project list for employee...");
		List<Project> userProjects = new ArrayList<Project>();
		for (Project p : projects) {
			if (p.getLeader() == user || p.getLeader() == null) {
				userProjects.add(p);
				System.out.println(p.getName());
			}
		}
		return userProjects;
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
		Project project = getProject(projectName);
		if (project.getLeader() == loggedInAs) {
			return "Project: " + projectName + "\n" + project.makeRepport();
		} else {
			throw new Exception("Only the project leader can get a project report");
		}
	}

	public void setStartDate(int startDay, int startMonth, int startYear) throws Exception {
		if (selectedProject.getLeader() != loggedInAs) {
			throw new Exception("Only the project leader can set a startdate");
		}
		checkValid(startDay, startMonth, startYear);
		selectedActivity.setStartDate(startDay, startMonth, startYear);
	}

	private void checkValid(int day, int month, int year) throws Exception {
		Calendar checker = new GregorianCalendar();
		checker.set(year, month, day);
		if (!(checker.get(Calendar.DATE) == day && checker.get(Calendar.MONTH) == month && checker.get(Calendar.YEAR) == year)) {
			throw new Exception("Must be a valid date");
		}
	}

	public Calendar getStartDate() throws Exception {
		Calendar output = selectedActivity.getStartDate();
		return output;
	}

	public void setDeadline(int deadDay, int deadMonth, int deadYear) throws Exception {
		if (selectedProject.getLeader() != loggedInAs) {
			throw new Exception("Only the project leader can set a deadline");
		}
		checkValid(deadDay, deadMonth, deadYear);
		selectedActivity.setDeadline(deadDay, deadMonth, deadYear);
	}

	public Calendar getDeadline() throws Exception {
		Calendar output = selectedActivity.getDeadline();
		return output;
	}

	public void startApp() {
		gui.start();
	}

	public List<Employee> getAvailableEmployees(GregorianCalendar date) {
		List<Employee> availableEmployees = new ArrayList<Employee>();
		for (Employee employee : employees) {
			if (employee.getAvailablePomodoro(date) <= threshold && employee != loggedInAs) {
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}
	
	public void removeEmployeeFromActivity(Employee employee) throws Exception {
		if (this.selectedProject.getLeader() == this.loggedInAs) {
			this.selectedActivity.removeEmployee(employee);
		} else {
			throw new Exception("Only the project leader can remove employees from activities");
		}
	}

	public Employee getEmployee(String initials) throws Exception {
		for (Employee employee : employees) {
			if (employee.getInitials().equals(initials)) {
				return employee;
			}
		}
		throw new Exception("An employee with the initials \"" + initials + "\" does not exsit");
	}
	
	public void addEmployeeToActivity(Employee employee) throws Exception {
		if (selectedProject.getLeader() != loggedInAs) {
			throw new Exception("Only the project leader can add the employee to the activity");
		}
		selectedActivity.addEmployee(employee);
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getThreshold() {
		return threshold;
	}

	public List<Employee> getEmployees() {
		return employees;		
	}

	public Employee getLoggedInAs() {
		return this.loggedInAs;
	}

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
}
