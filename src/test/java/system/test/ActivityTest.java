//Maximilian s204178
package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Activity;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;
import system.app.Project;

public class ActivityTest {
	private PKV system;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;

	// PKV stands for projekt kordinerings værktøj
	public ActivityTest(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employee = new Employee(initialer.getInitials());
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("the logged in employee is not the project leader of {string}")
	public void the_logged_in_employee_is_not_the_project_leader_of(String projectName) throws Exception {
		system.getProject(projectName).setLeader(null);
	}
	
	@When("the employee adds another employee with the initials {string} to the activity {string} under project {string}")
	public void the_employee_adds_another_employee_with_the_initials_to_the_activity_under_project(String initials, String activityName, String projectName) throws Exception {
		Employee employee = null;
		try {
			employee = system.getEmployee(initials);
			system.setSelectedProject(system.getProject(projectName));
			system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
			system.addEmployeeToActivity(employee);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee with the initials {string} is assigned to the activity {string} under project {string}")
	public void the_employee_with_the_initials_is_assigned_to_the_activity_under_project(String initials, String activityName, String projectName) throws Exception {
		Employee employee = system.getEmployee(initials);
		Project project = system.getProject(projectName);
		Activity activity = project.getActivity(activityName);
		assertTrue(employee.hasActivity(activity));
		assertTrue(activity.hasEmployee(employee));
	}
	
	@When("the employee removes the other employee with the initials {string} from the activity {string} under project {string}")
	public void the_employee_removes_the_other_employee_with_the_initials_from_the_activity_under_project(String initials, String activityName, String projectName) throws Exception {
		Employee employee = system.getEmployee(initials);
		Project project = system.getProject(projectName);
		Activity activity = project.getActivity(activityName);
		try {
			activity.removeEmployee(employee);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee with the initials {string} is not assigned to the activity {string} under project {string}")
	public void the_employee_with_the_initials_is_not_assigned_to_the_activity_under_project(String initials, String activityName, String projectName) throws Exception {
		Employee employee = system.getEmployee(initials);
		Project project = system.getProject(projectName);
		Activity activity = project.getActivity(activityName);
		assertFalse(employee.hasActivity(activity));
		assertFalse(activity.hasEmployee(employee));
	}
}
