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
	private String employeeInitials;

	// PKV stands for projekt kordinerings værktøj
	public LoginLogout(PKV system) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employeeInitials = initialer.getInitials();
	}


	@Given("that an employee is logged in")
	public void that_an_employee_is_logged_in() {
		this.system.login(employeeInitials);
		assertTrue(this.system.getLoggedInAs().getInitials().equals(employeeInitials));
	}

	@When("the employee logs out")
	public void the_employee_logs_out() {
		this.system.logOut();
	}

	@Then("the employee is logged out")
	public void the_employee_is_logged_out() {
		assert this.system.getLoggedInAs() == null;
	}
	
	@When("a second employee logs in")
	public void a_second_employee_logs_in() throws Exception {
		EmployeeHelper initialer = new EmployeeHelper();
		this.system.login(initialer.getInitials2());
	}
}

