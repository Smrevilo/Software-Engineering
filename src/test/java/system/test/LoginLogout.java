package system.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;

public class LoginLogout {
	private PKV system;
	private String employeeInitials;
	private ErrorMessageHolder errorMessageHolder;

	public LoginLogout(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employeeInitials = initialer.getInitials();
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("that an employee is logged in")
	public void that_an_employee_is_logged_in() throws Exception {
		this.system.login(employeeInitials);
	}
	
	@When("the employee logs in with the initials {string}")
	public void the_employee_logs_in_with_the_initials(String initials) {
		try {
			this.system.login(initials);
		} catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee logs out")
	public void the_employee_logs_out() {
		this.system.logOut();
	}

	@Then("the employee is logged out")
	public void the_employee_is_logged_out() {
		assertThat(this.system.getLoggedInAs(), is(nullValue()));
	}
	
	@When("a second employee logs in")
	public void a_second_employee_logs_in() throws Exception {
		EmployeeHelper initialer = new EmployeeHelper();
		this.system.login(initialer.getInitials2());
	}
}

