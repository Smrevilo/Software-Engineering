package system.test;

import static org.junit.Assert.assertFalse;

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

	@Then("every employee has theese activities assigned, with the start date {int} {int} {int} , and the deadline {int} {int} {int} and they are acitve, for the project {string}")
	public void every_employee_has_theese_activities_assigned_with_the_start_date_and_the_deadline_and_they_are_acitve(
			int startDay, int startMonth, int startYear, int deadDay, int deadMonth, int deadYear, String project,
			List<String> activityNames) throws Exception {
		for (String activity : activityNames) {
			system.setSelectedProject(system.getProject(project));
			system.getSelectedProject().createActivty(activity);
			system.setStartDateFor(project, activity, startDay, startMonth, startYear);
			system.setDeadlineFor(project, activity, deadDay, deadMonth, deadYear);
			system.setStatefor(project, activity, true);

		}
		ArrayList<Activity> activityList = system.getSelectedProject().getAcivityList();

		for (Employee employee : system.getEmployeeList()) {
			for (Activity activity : activityList) {
				activity.addEmployee(employee);
				// System.out.println("employee has been asigned a activity: " + employee + " ||
				// " + activity);
			}
		}
	}


	@Given("another employee is assigned to nine activities, with the start date {int} {int} {int} , and the deadline {int} {int} {int} and the project name {string}")
	public void another_employee_is_assigned_to_nine_activities_with_the_start_date_and_the_deadline_and_the_project_name(
			int startDay, int startMonth, int startYear, int deadDay, int deadMonth, int deadYear, String project,
			List<String> activityNames) throws Exception {

		for (String activity : activityNames) {
			system.setSelectedProject(system.getProject(project));
			system.getSelectedProject().createActivty(activity);
			system.setStartDateFor(project, activity, startDay, startMonth, startYear);
			system.setDeadlineFor(project, activity, deadDay, deadMonth, deadYear);
			system.setStatefor(project, activity, true);
		}
		ArrayList<Activity> activityList = system.getSelectedProject().getAcivityList();
		
		// denne kode er til at fjerne aktiviteter
//		for (Activity activity : activityList) {
//			system.removeEmployeeFromActivity(system.getEmployeeList().get(0));
//		}
		
		dummy = new Employee("dummy");
		for (Activity activity : activityList) {
			activity.addEmployee(this.dummy);
			System.out.println("dummy has been asigned a activity: " + "dummy" + " || " +
			 activity);
		}

	}

	
	
	@When("the logged in employee looks for availability at the date {int} {int} {int}")
	public void the_logged_in_employee_looks_for_availability_at_the_date(int day, int month, int year) {
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		availableEmployee = system.getAvailableEmployee(date);
	}

//	@Then("return a list of employees that are available")
//	public void return_a_list_of_employees_that_are_available() {
//		errorMessageHolder.setErrorMessage("");
//		for (Employee employee : availableEmployee) {
//
//			if (!(employee.getPomodoroAmount() <=10)) {
//				
//				errorMessageHolder.setErrorMessage("Error in list of available employees");
//				
//			}
//		
//		}
//		assertFalse(errorMessageHolder.getErrorMessage().equals("Error in list of available employees"));
//	}

	
	@Then("display a list of {int} employee that are available at that date")
	public void display_a_list_of_employee_that_are_available_at_that_date(int i) {
		System.out.println("Avivialbe employees: " + availableEmployee.size());
	}
}
