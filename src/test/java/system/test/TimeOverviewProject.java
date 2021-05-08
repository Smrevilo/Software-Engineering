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
	private String report;

	// PKV stands for projekt kordinerings værktøj
	public TimeOverviewProject(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Then("the project has these activities")
	public void the_project_has_these_activities(List<String> activityNames) throws Exception {
		for (String name : activityNames) {
			system.getSelectedProject().createActivty(system.getLoggedInAs(), name);
		}
	}
	
	@Given("the activity {string} has the employee assigned")
	public void the_activity_has_the_employee_assigned(String activityName) throws Exception {
	    system.getSelectedProject().getActivity(activityName).addEmployee(system.getLoggedInAs());
	}

	@Then("the activity {string} has {int} registred hours")
	public void the_activity_has_registred_hours(String activityName, Integer hours) throws Exception {
	    system.getSelectedProject().getActivity(activityName).addTime(system.getLoggedInAs(), hours);
	}

	
	@Given("the employee requests to view total time of {string}")
	public void the_employee_requests_to_view_total_time_of(String projectName) {
	    try {
	    	report = system.makeReportFor(projectName);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		
		}
	}

	@Then("display a list of the work hours each employee is assigned to")
	public void display_a_list_of_the_work_hours_each_employee_is_assigned_to() {
	    System.out.println(report);
	}
	
	@Then("display an empty list of the work hours each employee is assigned to")
	public void display_an_empty_list_of_the_work_hours_each_employee_is_assigned_to() {
		String report = system.getSelectedProject().makeReport();
	    System.out.println(report);
	    assertTrue(report.equals("There is no activites"));
	}
}
