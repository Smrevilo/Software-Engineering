package WhiteboxTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import system.app.Activity;
import system.app.Employee;
import system.app.PKV;
import system.app.Pomodoro;
import system.app.Project;

class WhiteBoxTimeReg {
	
//	public void addTime(Employee employee, int i) throws Exception {
//		boolean found = false;
//		for (Pomodoro pomodoro : pomodoros) {
//			if (pomodoro.getEmployee() == employee) {   //1
//				pomodoro.addTime(i);                    //1i
//				found=true;
//			}
//		}
//		if (!found) {                                   //2
//			throw new Exception("You are not assigned to this activity");
//		}
//	}

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
	void positiveArg() {
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
	
	@Test
	void notAssigned() throws Exception{
		selectedProject = pkvSystem.getProject("ABCE");
		selectedActivty = selectedProject.getActivity("Sick Days");
		
		assertTrue(selectedActivty.getTotalTime() == 0);
		
		try {
			selectedActivty.addTime(eUser, 21);
		} catch (Exception e) {}
		
		assertTrue(selectedActivty.getTotalTime() == 0);
	}
	
	@Test
	void newAssigned() throws Exception {
		Employee eUser2 = pkvSystem.getEmployee("ABCE");
		selectedActivty.addEmployee(eUser2);
		
		assertTrue(selectedActivty.getTotalTime() == 0);
		
		try {
			selectedActivty.addTime(eUser2, 123);
		} catch (Exception e) {}
		
		assertTrue(selectedActivty.getTotalTime() == 123);
	}
	
	@Test
	void multipleAssigned() throws Exception {
		Employee eUser2 = pkvSystem.getEmployee("ABCE");
		selectedActivty.addEmployee(eUser2);
		try {
			selectedActivty.addTime(eUser, 1);
		} catch (Exception e) {}
		
		assertTrue(selectedActivty.getTotalTime() == 1);
		
		try {
			selectedActivty.addTime(eUser2, 10);
		} catch (Exception e) {}
		
		assertTrue(selectedActivty.getTotalTime() == 11);
	}

}
