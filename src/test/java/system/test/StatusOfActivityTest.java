package system.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.app.Employee;
import system.app.PKV;

public class StatusOfActivityTest {

	private PKV system;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;

	
	public StatusOfActivityTest(PKV system, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.system = system;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("the project leader adds the activity {string}")
	public void the_project_leader_adds_the_activity(String activity) throws Exception {
		system.getSelectedProject().createActivty(system.getSelectedProject().getLeader(), activity);
		
	}

	@When("the project leader sets the status of the activity {string} under the project {string} to {string}")
	public void the_project_leader_sets_the_status_of_the_activity_under_the_project_to(String activity, String project,
			String status) {

		try {
			system.getProject(project).getActivity(activity).setStatusOfActivity(status);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} under the project {string} has the status {string}")
	public void the_activity_under_the_project_has_the_status(String activity, String project, String status) throws Exception {
		system.setSelectedProject(system.getProject(project));
		system.setSelectedActivity(system.getSelectedProject().getActivity(activity));
		boolean activityStatus = false;
		System.out.println(status);
		if (status.equals("Done")) {
			assertThat(system.getSelectedActivity().getStatusOfActivity(), is(true));
		} else if (status.equals("Not Done")) {
			assertThat(system.getSelectedActivity().getStatusOfActivity(), is(false));
		} 

		
	}
}
