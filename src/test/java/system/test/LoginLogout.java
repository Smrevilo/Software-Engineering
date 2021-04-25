package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;

public class LoginLogout {
	private PKV system;
	private Employee employee; 

	// PKV stands for projekt kordinerings værktøj
	public LoginLogout(PKV system) {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employee = new Employee(initialer.getInitials());
		this.system.add(employee);
	}


	@Given("that an employee is logged in")
	public void that_an_employee_is_logged_in() {
		this.system.login(employee);
		assertTrue(this.system.getLoggedInAs().equals(employee));
	}

	@When("the employee logs out")
	public void the_employee_logs_out() {
		this.system.logOut();
	}

	@Then("the employee is logged out")
	public void the_employee_is_logged_out() {
		assert this.system.getLoggedInAs() == null;
	}
}

