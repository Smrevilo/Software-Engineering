// Mads s204170
package WhiteboxTests;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import system.app.Activity;
import system.app.Employee;
import system.app.PKV;
import system.app.Pomodoro;
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
	void positiveArg() {
		assertThat(selectedActivty.getTotalTime(), is(0));
		assertDoesNotThrow(() -> selectedActivty.addTime(eUser, 10));
		assertThat(selectedActivty.getTotalTime(), is(10));
	}
	
	@Test
	void negativeArg() {
		assertThat(selectedActivty.getTotalTime(), is(0));
		assertThrows(Exception.class,() -> selectedActivty.addTime(eUser, -10));
		assertThat(selectedActivty.getTotalTime(), is(0));
	}
	
	@Test
	void gaussTest() throws Exception {
		assertThat(selectedActivty.getTotalTime(), is(0));
		for (int i = 1; i <= 100; i++) {
			selectedActivty.addTime(eUser, i);
		}
		assertThat(selectedActivty.getTotalTime(), is(5050));
	}
	
	@Test
	void notAssigned() throws Exception{
		selectedProject = pkvSystem.getProject("ABCE");
		selectedActivty = selectedProject.getActivity("Sick Days");

		assertThat(selectedActivty.getTotalTime(), is(0));
		assertThrows(Exception.class,() -> selectedActivty.addTime(eUser, 21));
		assertThat(selectedActivty.getTotalTime(), is(0));
	}
	
	@Test
	void multipleAssigned() throws Exception {
		Employee eUser2 = pkvSystem.getEmployee("ABCE");
		selectedActivty.addEmployee(eUser2);
		assertDoesNotThrow(() -> selectedActivty.addTime(eUser, 1));

		assertThat(selectedActivty.getTotalTime(), is(1));
		assertDoesNotThrow(() -> selectedActivty.addTime(eUser2, 10));
		assertThat(selectedActivty.getTotalTime(), is(11));
	}
	
	@Test
	void intOverload() {
		assertThat(selectedActivty.getTotalTime(), is(0));
		assertThrows(Exception.class,() -> selectedActivty.addTime(eUser, 2147483647));
		assertThat(selectedActivty.getTotalTime(), is(0));
	}

}
