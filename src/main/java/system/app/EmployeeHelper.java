package system.app;

import java.util.*;

public class EmployeeHelper {
	private List<Employee> employeeList;
	private List<Project> projects;

	public EmployeeHelper() throws Exception {
		employeeList = new ArrayList<>();
		String[] initialer = {"ABCD", "ABCE","ABCF","ABCG","ABCH","ABCI","ABCJ","ABCK","ABCL","ABCM","ABCN",
				"ABCO","ABCP","ABCQ","ABCR","ABCS","ABCT","ABCU", "ABCV","ABCX","ABCY","ABCZ","EBCD","FBCD","GBCD","HBCD","IBCD","JBCD",
				"KBCD","LBCD"};
		for(String in : initialer) {
			employeeList.add(new Employee(in));
		}
		
		projects = new ArrayList<>();
		
		for(String in : initialer) {
			projects.add(new Project(in, 999999));
		}

		for (int i = 0; i < initialer.length; i++) {
			Activity temp;
			
			projects.get(i).createActivty("Sick Days");
			temp = projects.get(i).getActivity("Sick Days");
			temp.addEmployee(employeeList.get(i));
			
			projects.get(i).createActivty("Vacation");
			temp = projects.get(i).getActivity("Vacation");
			temp.addEmployee(employeeList.get(i));
			
			projects.get(i).createActivty("Courses");
			temp = projects.get(i).getActivity("Courses");
			temp.addEmployee(employeeList.get(i));
		}
	}

	public String getInitials() {
		return employeeList.get(0).getInitials();
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public List<Project> getProjectList() {
		return projects;
	}
	
	public String getInitials2() {
		return employeeList.get(1).getInitials();
	}
}
