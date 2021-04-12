package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dtu.calculator.ErrorMessageHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;

public class Project {
	private PKV system;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;

	// PKV stands for projekt kordinerings værktøj
	public Project(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employee = new Employee(initialer.getInitials());
		this.system.add(employee);
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the employee creates a project using the name {string}")
	public void the_employee_create_a_project_using_the_name(String name) {
	    try {
	    	system.createProject(name);
	    } catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("a project with the name {string} exsits")
	public void a_project_with_the_name_exsits(String name) {
		assertTrue(system.hasProject(name));
	}
	
	@Given("a project with the name {string} has been created")
	public void a_project_with_the_name_has_been_created(String name) throws Exception {
	    system.createProject(name);
	}

	@Then("an error containing {string} is displayed")
	public void an_error_containing_is_displayed(String errorMsg) {
		assertTrue(errorMessageHolder.getErrorMessage().equals(errorMsg));
	}

}
