package WhiteboxTests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import system.app.Employee;
import system.app.PKV;

public class WhiteBoxDeleteProject {
	PKV system;
	Employee employee;

	public WhiteBoxDeleteProject() throws Exception {
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
		assertThrows(Exception.class, () -> system.deleteProject("#NOT#FailProject"));
	}
	@Test
	void deleteProjectWhileNotBeingLeaderError() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		assertThrows(Exception.class, () -> system.deleteProject("FailProject"));
	}	
}
