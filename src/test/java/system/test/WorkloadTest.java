// Mads s204170
package system.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;
import system.app.Project;

public class WorkloadTest {
	private PKV system;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;

	// PKV stands for projekt kordinerings værktøj
	public WorkloadTest(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employee = new Employee(initialer.getInitials());
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the logged in employee sets the workload of the activity {string} under the project {string} to {int}")
	public void the_logged_in_employee_sets_the_workload_of_the_activity_under_the_project_to_s(String activityName, String projectName, int workload) throws Exception {
	    system.setSelectedProject(system.getProject(projectName));
	    system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
	    try {
	    	system.setWorkload(workload);
	    } catch (Exception e) {
	    	this.errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the activity {string} under the project {string} has the workload set to {int}")
	public void the_activity_under_the_project_has_the_workload_set_to(String activityName, String projectName, int workload) throws Exception {
		system.setSelectedProject(system.getProject(projectName));
	    system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
	    assertThat(system.getSelectedActivity().getWorkload(), is(workload));
	}

}
