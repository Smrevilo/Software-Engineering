//Oliver s204182
package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.*;

public class TimeOverviewEmployee {
	private PKV system;
	private ErrorMessageHolder errorMessageHolder;

	// PKV stands for projekt kordinerings værktøj
	public TimeOverviewEmployee(PKV system, ErrorMessageHolder errorMessageHolder) {
		this.system = system;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("the employee requests own work hours for the activity {string}")
	public void the_employee_requests_own_work_hours_for_the_activity(String activityName) {
		String repport;
		try {
			repport = system.getLoggedInAs().makeRepport(activityName);
			System.out.println(repport);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee requests own work hours for all the activities")
	public void the_employee_requests_own_work_hours_for_all_the_activities() {
		 String repport = system.getLoggedInAs().makeRepport();
		 System.out.println(repport);
	}
}
