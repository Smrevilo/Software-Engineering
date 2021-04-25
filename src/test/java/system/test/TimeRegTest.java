package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import dtu.calculator.ErrorMessageHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.*;

public class TimeRegTest {
	private PKV system;

	// PKV stands for projekt kordinerings værktøj
	public TimeRegTest(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;

	}

	@Given("this project has this activity {string}")
	public void this_project_has_this_activity(String activity) {
		this.system.getSelectedProject().createActivty(activity);
	}

	@Given("that the employee is assigned to the activity with the name {string} under the project {string}")
	public void that_the_employee_is_assigned_to_the_activity_with_the_name_under_the_project(String string, String string2) {
	    Project projekt=system.getProject(string2);
	    Activity activity= projekt.getActivity(string);
	    activity.addEmployee(system.getLoggedInAs());
	    
	}

	@When("the employee registers time to the activity with the name {string} under the project {string}")
	public void the_employee_registers_time_to_the_activity_with_the_name_under_the_project(String string, String string2) {
		 Project projekt=system.getProject(string2);
		 Activity activity= projekt.getActivity(string);
		 activity.addtime(system.getLoggedInAs(), 10);
	}

	@Then("the time is registered to the activity with the name {string} under the project {string}")
	public void the_time_is_registered_to_the_activity_with_the_name_under_the_project(String string, String string2) {
		Project projekt=system.getProject(string2);
	    Activity activity= projekt.getActivity(string);
	    assertTrue(activity.getTotalTime()==10);
	}
	
	@Given("that the employee is Not assigned to the activity with the name {string} under the project {string}")
	public void that_the_employee_is_not_assigned_to_the_activity_with_the_name_under_the_project(String string, String string2) {
		Project projekt=system.getProject(string2);
	    Activity activity= projekt.getActivity(string);
	    assertFalse(activity.isAssignedTo(system.getLoggedInAs()));
	}

	@Then("the time is not registered to the activity with the name {string} under the project {string}")
	public void the_time_is_not_registered_to_the_activity_with_the_name_under_the_project(String string, String string2) {
		Project projekt=system.getProject(string2);
	    Activity activity= projekt.getActivity(string);
	    assertTrue(activity.getTotalTime()==0);
	}
	
	@When("the employee registers time to sick days")
	public void the_employee_registers_time_to_sick_days() {
		system.setSelectedProject(system.getProject(system.getLoggedInAs().getInitials()));
		Activity activity = system.getSelectedProject().getActivity("Sick Days");
		activity.addtime(system.getLoggedInAs(), 10);
	}

	@Then("the time is registered under sick days")
	public void the_time_is_registered_under_sick_days() {
		assertTrue(system.getLoggedInAs().getTimefor("Sick Days")==10);
	}



}
