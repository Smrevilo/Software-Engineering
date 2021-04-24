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
import system.app.Project;

public class ProjectTest {
	private PKV system;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;
	private Project selectedProject;
	private Employee dummy = new Employee("dummy");

	// PKV stands for projekt kordinerings værktøj
	public ProjectTest(PKV system, ErrorMessageHolder errorMessageHolder) {
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

	@Then("a project with the name {string} exists")
	public void a_project_with_the_name_exists(String name) {
		assertTrue(system.hasProject(name));
		selectedProject = system.getProject(name);
	}
	
	@Given("a project with the name {string} has been created")
	public void a_project_with_the_name_has_been_created(String name) throws Exception {
	    system.createProject(name);
	}

	@Then("an error containing {string} is displayed")
	public void an_error_containing_is_displayed(String errorMsg) {
		assertTrue(errorMessageHolder.getErrorMessage().equals(errorMsg));
	}
	
	//EDIT PROJECT ----------------------------------------------------------

	@When("the project leader adds the activity {string}")
	public void the_project_leader_adds_the_activity(String string) {
		System.out.println("project: "+selectedProject);
		System.out.println(string);
		selectedProject.createActivty(string);
		assertTrue(true);
	}

	@Then("the project has the activity {string}")
	public void the_project_has_the_activity(String string) {
		assertTrue(selectedProject.hasActivty(string));
	}
	
	@Given("that the project has no project leader assigned")
	public void that_the_project_has_no_project_leader_assigned() {
		if (selectedProject.getLeader() == null) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	@When("an employee is set as the project leader")
	public void an_employee_is_set_as_the_project_leader() {
		selectedProject.setLeader(dummy);
	}

	@Then("the project has a project leader")
	public void the_project_has_a_project_leader() {
		if (selectedProject.getLeader() == dummy) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}


}
