package system.app;

import java.util.*;

public class EmployeeHelper {
	private List<Employee> employeeList;
	private List<Project> projects;

	public EmployeeHelper() throws Exception {
		employeeList = new ArrayList<>();
		String[] initialer = { "ABCD", "ABCE", "ABCF", "ABCG", "ABCH", "ABCI", "ABCJ", "ABCK", "ABCL", "ABCM", "ABCN",
				"ABCO", "ABCP", "ABCQ", "ABCR", "ABCS", "ABCT", "ABCU", "ABCV", "ABCX", "ABCY", "ABCZ", "EBCD", "FBCD",
				"GBCD", "HBCD", "IBCD", "JBCD", "KBCD", "LBCD" };

		projects = new ArrayList<>();

		for (String in : initialer) {
			Employee tempE = new Employee(in);
			employeeList.add(tempE);
			Project tempP = new Project(in, 999999);
			projects.add(tempP);
			tempP.setLeader(tempE);
		}

		for (int i = 0; i < initialer.length; i++) {
			Activity temp;

			projects.get(i).createActivty(employeeList.get(i), "Sick Days");
			temp = projects.get(i).getActivity("Sick Days");
			temp.addEmployee(employeeList.get(i));
			temp.setEditable(false);

			projects.get(i).createActivty(employeeList.get(i), "Vacation");
			temp = projects.get(i).getActivity("Vacation");
			temp.addEmployee(employeeList.get(i));
			temp.setEditable(false);

			projects.get(i).createActivty(employeeList.get(i), "Courses");
			temp = projects.get(i).getActivity("Courses");
			temp.addEmployee(employeeList.get(i));
			temp.setEditable(false);
			
			projects.get(i).setEditable(false);
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
