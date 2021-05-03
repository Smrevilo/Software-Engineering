package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.*;

public class SetStartDateAndDeadline {
	private PKV system;
	private ErrorMessageHolder errorMessageHolder;
	
	
	
	public SetStartDateAndDeadline(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the employee sets the startdate of the activity {string} under the project {string} to the {int} - {int} - {int}")
	public void the_employee_sets_the_startdate_of_the_activity_under_the_project_to_the(String activityName, String projectName, int startdate, int startmonth,int startyear) {
	    try {
			system.setStartDateFor(projectName, activityName, startdate, startmonth, startyear);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the acitivity {string} under the project {string} has a startdate that says {int} - {int} - {int}")
	public void the_acitivity_under_the_project_has_a_startdate_that_says(String activityName, String projectName, int startdate, int startmonth,int startyear) throws Exception {
		Calendar check = system.getStartDateFor(projectName, activityName);

		assertTrue(check.get(check.DAY_OF_MONTH) == startdate);
		assertTrue(check.get(check.MONTH) == startmonth);
		assertTrue(check.get(check.YEAR) == startyear);
	
	}
	
	@When("the employee sets the deadline of the activity {string} under the project {string} to the {int} - {int} - {int}")
	public void the_employee_sets_the_deadline_of_the_activity_under_the_project_to_the(String activityName, String projectName, int deadDate, int deadMonth,int deadYear) {
		try {
			system.setDeadlineFor(projectName, activityName, deadDate, deadMonth, deadYear);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the acitivity {string} under the project {string} has a deadline that says {int} - {int} - {int}")
	public void the_acitivity_under_the_project_has_a_deadline_that_says(String activityName, String projectName, int deadDate, int deadMonth,int deadYear) throws Exception {
		Calendar check = system.getDeadlineFor(projectName, activityName);

		assertTrue(check.get(check.DAY_OF_MONTH) == deadDate);
		assertTrue(check.get(check.MONTH) == deadMonth);
		assertTrue(check.get(check.YEAR) == deadYear);
	}


}
