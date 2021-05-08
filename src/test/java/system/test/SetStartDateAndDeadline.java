package system.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
	    	system.setSelectedProject(system.getProject(projectName));
			system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
			system.setStartDate(startdate, startmonth, startyear);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the acitivity {string} under the project {string} has a startdate that says {int} - {int} - {int}")
	public void the_acitivity_under_the_project_has_a_startdate_that_says(String activityName, String projectName, int startdate, int startmonth,int startyear) throws Exception {

		system.setSelectedProject(system.getProject(projectName));
		system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
		
		Calendar check = system.getStartDate();

		assertThat(check.get(check.DAY_OF_MONTH), is(startdate));
		assertThat(check.get(check.MONTH), is(startmonth));
		assertThat(check.get(check.YEAR), is(startyear));
	
	}
	
	@When("the employee sets the deadline of the activity {string} under the project {string} to the {int} - {int} - {int}")
	public void the_employee_sets_the_deadline_of_the_activity_under_the_project_to_the(String activityName, String projectName, int deadDate, int deadMonth,int deadYear) {
		try {
			system.setSelectedProject(system.getProject(projectName));
			system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
			system.setDeadline(deadDate, deadMonth, deadYear);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the acitivity {string} under the project {string} has a deadline that says {int} - {int} - {int}")
	public void the_acitivity_under_the_project_has_a_deadline_that_says(String activityName, String projectName, int deadDate, int deadMonth,int deadYear) throws Exception {
		system.setSelectedProject(system.getProject(projectName));
		system.setSelectedActivity(system.getSelectedProject().getActivity(activityName));
		Calendar check = system.getDeadline();

		assertThat(check.get(check.DAY_OF_MONTH), is(deadDate));
		assertThat(check.get(check.MONTH), is(deadMonth));
		assertThat(check.get(check.YEAR), is(deadYear));
	}


}
