//Viktor s204166
package system.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.*;

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

	// PKV stands for projekt kordinerings værktøj
	public ProjectTest(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employee = new Employee(initialer.getInitials());
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the employee creates a project using the name {string}")
	public void the_employee_create_a_project_using_the_name(String name) {
	    try {
	    	system.createProject(name);
			system.setSelectedProject(system.getProject(name));
	    } catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("a project with the name {string} exists")
	public void a_project_with_the_name_exists(String name) {
		assertTrue(system.hasProject(name));
	}
	
	@Given("a project with the name {string} has been created")
	public void a_project_with_the_name_has_been_created(String name) throws Exception {
	    system.createProject(name);
	    system.setSelectedProject(system.getProject(name));
	}
	
	//EDIT PROJECT ----------------------------------------------------------

	@When("the logged in employee adds the activity {string}")
	public void the_project_leader_adds_the_activity(String string) {
		try {
			system.getSelectedProject().createActivty(system.getLoggedInAs(), string);
	    } catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the project has the activity {string}")
	public void the_project_has_the_activity(String acivityName) {
		assertTrue(system.getSelectedProject().hasActivity(acivityName));
	}
	
	@Given("that the project has no project leader assigned")
	public void that_the_project_has_no_project_leader_assigned() {
		system.getSelectedProject().setLeader(null);
	}

	@When("an employee is set as the project leader of the project {string}")
	public void an_employee_is_set_as_the_project_leader_of_the_project(String projectName) {
		try {
			system.getProject(projectName).setLeader(system.getLoggedInAs());
	    } catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the project has a project leader")
	public void the_project_has_a_project_leader() {
		assertTrue(system.getSelectedProject().getLeader().equals(system.getLoggedInAs()));
	}
	
	@Given("the logged in employee is the project leader of {string}")
	public void the_logged_in_employee_is_the_project_leader_of(String projectName) throws Exception {
		Employee leader = system.getLoggedInAs();
		system.getProject(projectName).setLeader(leader);
		system.setSelectedProject(system.getProject(projectName));
		assertThat(system.getProject(projectName).getLeader(), is(system.getLoggedInAs()));
	}
	
	@When("the employee deletes the project {string}")
	public void the_employee_deletes_the_project(String projectName) {
	    try {
			system.deleteProject(projectName);
		} catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project {string} no longer exists")
	public void the_project_no_longer_exists(String projectName) {
	    assertFalse(system.hasProject(projectName));
	}
	
	@When("the logged in employee deletes the activity {string}")
	public void the_logged_in_employee_deletes_the_activity(String string){
		try {
			system.getSelectedProject().deleteActivity(string);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project don't have the activity {string}")
	public void the_project_don_t_have_the_activity(String string) {
		assertThrows(Exception.class,() -> system.getSelectedProject().getActivity(string));
	}
	
}
