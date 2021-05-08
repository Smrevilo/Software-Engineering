package system.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import system.app.Activity;
import system.app.Employee;
import system.app.PKV;
import system.app.Project;

class WhiteBoxTimeReg {

	PKV pkvSystem;
	Employee eUser;
	Project selectedProject;
	Activity selectedActivty;
	
	public WhiteBoxTimeReg() throws Exception {
		pkvSystem = new PKV();
		pkvSystem.login("ABCD");
		eUser = pkvSystem.getEmployee("ABCD");
		selectedProject = pkvSystem.getProject("ABCD");
		selectedActivty = selectedProject.getActivity("Sick Days");
	}
	
	@Test
	void path1() {
		assertTrue(selectedActivty.getTotalTime() == 0);
		try {
			selectedActivty.addTime(eUser, 10);
		} catch (Exception e) {}
		assertTrue(selectedActivty.getTotalTime() == 10);
	}
	
	@Test
	void negativeArg() {
		assertTrue(selectedActivty.getTotalTime() == 0);
		try {
			selectedActivty.addTime(eUser, -10);
		} catch (Exception e) {}
		assertTrue(selectedActivty.getTotalTime() == 0);
	}
	
	@Test
	void gaussTest() {
		assertTrue(selectedActivty.getTotalTime() == 0);
		for (int i = 1; i <= 100; i++) {
			try {
				selectedActivty.addTime(eUser, i);
			} catch (Exception e) {}
		}
		assertTrue(selectedActivty.getTotalTime() == 5050);
	}

}
