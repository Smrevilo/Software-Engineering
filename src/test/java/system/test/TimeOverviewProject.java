package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.*;


public class TimeOverviewProject {
	private PKV system;
	private ErrorMessageHolder errorMessageHolder;

	// PKV stands for projekt kordinerings værktøj
	public TimeOverviewProject(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Then("the project has these activities")
	public void the_project_has_these_activities(List<String> activityNames) {
		for (String name : activityNames) {
			system.getSelectedProject().createActivty(name);
		}
	}
	
	@Then("the activity {string} has the employee assigned")
	public void the_activity_has_the_employee_assigned(String activityName) {
	    system.getSelectedProject().getActivity(activityName).addEmployee(system.getLoggedInAs());
	}

	@Then("the activity {string} has {int} registred hours")
	public void the_activity_has_registred_hours(String activityName, Integer hours) throws Exception {
	    system.getSelectedProject().getActivity(activityName).addTime(system.getLoggedInAs(), hours);
	}

	
	@Given("the project leader requests to view total time of {string}")
	public void the_project_leader_requests_to_view_total_time_of(String projectName) {
	    system.setSelectedProject(system.getProject(projectName));
	}

	@Then("show the project leader a list of the work hours each employee is assigned to")
	public void show_the_project_leader_a_list_of_the_work_hours_each_employee_is_assigned_to() {
	    String repport = system.getSelectedProject().makeRepport();
	    System.out.println(repport);
	}
	
	@Then("show the project leader an empty list of the work hours each employee is assigned to")
	public void show_the_project_leader_an_empty_list_of_the_work_hours_each_employee_is_assigned_to() {
		String repport = system.getSelectedProject().makeRepport();
	    System.out.println(repport);
	    assertTrue(repport.equals("There is no activites"));
	}
}
