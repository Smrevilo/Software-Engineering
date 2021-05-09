package WhiteboxTests;

import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import system.app.Employee;
import system.app.PKV;

public class DeleteProjectWhiteBox {
	PKV system;
	Employee employee;

	public DeleteProjectWhiteBox() throws Exception {
		system = new PKV();
		system.login("ABCD");
		employee = system.getEmployee("ABCD");
	}
	
	@Test
	void deleteSuccessfully() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		system.getProject("FailProject").setLeader(employee);
		system.deleteProject("FailProject");
		assertFalse(system.hasProject("FailProject"));

	}
	@Test
	void deleteProjectThatdoesNotExisError() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		system.getProject("FailProject").setLeader(employee);
		try {
			system.deleteProject("#NOT#FailProject");
		    Assert.fail("Failure in DeleteProjectWhiteBox: Cannot delete project that does not exist");
		} 
		catch (Exception e) {
		    String expectedException = "A project with that name doesn't exits";
		    Assert.assertEquals(expectedException, e.getMessage());
		}
	}
	@Test
	void deleteProjectWhileNotBeingLeaderError() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		try {
			system.deleteProject("FailProject");
		    Assert.fail("Failure in DeleteProjectWhiteBox: Cannot delete project if employee is not the project leader");
		} 
		catch (Exception e) {
		    String expectedException = "Only the project leaders can delete the project";
		    Assert.assertEquals(expectedException, e.getMessage() );
		}
	}
	
	
}
