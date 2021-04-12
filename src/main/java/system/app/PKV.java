package system.app;

import java.util.ArrayList;

public class PKV {
	private Employee loggedInAs;
	private ArrayList<Employee> employees;

	public PKV() {
		this.employees = new ArrayList<Employee>();
		
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

}
