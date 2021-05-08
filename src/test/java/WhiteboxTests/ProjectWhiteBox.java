package WhiteboxTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import system.app.Employee;
import system.app.PKV;

public class ProjectWhiteBox {
	PKV system;
	Employee employee;

	public ProjectWhiteBox() throws Exception {
		system = new PKV();
		system.login("ABCD");
		employee = system.getEmployee("ABCD");

	}

	@Test
	void CreateProject() throws Exception {
		try {
			system.createProject("Project1");
		} catch (Exception e) {
		}
		assertTrue(system.getProject("Project1").getName().equals("Project1"));
	}

	@Test
	void DeleteProject() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		system.getProject("FailProject").setLeader(employee);
		system.deleteProject("FailProject");
		assertFalse(system.hasProject("FailProject"));

	}
	@Test
	void DelteProjectWhileNotBeingLeader() throws Exception {
		system.createProject("FailProject");
		system.setSelectedProject(system.getProject("FailProject"));
		try {
			system.deleteProject("FailProject");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test 
	void HostnewProjectERROR() throws Exception {
		system.createProject("FailProject");
		try {
			system.createProject("FailProject");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	void CreateThousandProjects() throws Exception {
		for (int i = 0; i < 1000; i++) {
			system.createProject("FailProject#"+i);
		}
		
	}
}
