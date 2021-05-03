package system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Activity;
import system.app.Employee;
import system.app.EmployeeHelper;
import system.app.PKV;
import system.app.Project;

public class AvailabilityTest {
	private Employee dummy;
	private PKV system;
	private String employeeInitials;
	private Project projekt;
	private List<Employee> employeeList, availableEmployee;
	private ErrorMessageHolder errorMessageHolder;
	private int threshold;

	public AvailabilityTest(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		EmployeeHelper initialer = new EmployeeHelper();
		this.employeeInitials = initialer.getInitials();
		this.employeeList = initialer.getEmployeeList();
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("The threshold for the amount of activities that decides if a empolyee is available is {int}")
	public void the_threshold_for_the_amount_of_activities_that_decides_if_a_empolyee_is_available_is(int threshold) {
		system.setThreshold(threshold);
	}

	@Then("every employee has theese activities assigned, with the start date {int} {int} {int} , and the deadline {int} {int} {int}, for the project {string}")
	public void every_employee_has_theese_activities_assigned_with_the_start_date_and_the_deadline_and_they_are_acitve(
			int startDay, int startMonth, int startYear, int deadDay, int deadMonth, int deadYear, String projectName,
			List<String> activityNames) throws Exception {
		system.setSelectedProject(system.getProject(projectName));
		for (String activityName : activityNames) {
			system.getSelectedProject().createActivty(activityName);
			system.setStartDateFor(projectName, activityName, startDay, startMonth, startYear);
			system.setDeadlineFor(projectName, activityName, deadDay, deadMonth, deadYear);
		}
		ArrayList<Activity> activityList = system.getSelectedProject().getActivities();

		for (Activity activity : activityList) {
			for (Employee employee : system.getEmployees()) {
				activity.addEmployee(employee);
			}
		}
	}
	
	@When("the logged in employee looks for availability at the date {int} {int} {int}")
	public void the_logged_in_employee_looks_for_availability_at_the_date(int day, int month, int year) {
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		availableEmployee = system.getAvailableEmployees(date);
	}
	
	@Then("display a list of {int} employee that are available at that date")
	public void display_a_list_of_employee_that_are_available_at_that_date(int numAvailable) {
		assertTrue(availableEmployee.size() == numAvailable);
	}
}
