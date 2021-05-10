//Maximilian s204178
package WhiteboxTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import system.app.Activity;
import system.app.Employee;
import system.app.PKV;

public class WhiteBoxRemoveEmployee {
	PKV pkv;
	Employee employee;
	Activity activity;

	public WhiteBoxRemoveEmployee() throws Exception {
		pkv = new PKV();
		pkv.login("ABCD");
		employee = pkv.getEmployee("ABCD");
		pkv.createProject("TestProject");
		pkv.setSelectedProject(pkv.getProject("TestProject"));
		pkv.getSelectedProject().setLeader(employee);
		pkv.getSelectedProject().createActivty(employee, "TestActivity");
		pkv.setSelectedActivity(pkv.getSelectedProject().getActivity("TestActivity"));
	}
	@Test
	void removeEmployeeSucessfully() throws Exception {
		assertFalse(pkv.getSelectedActivity().hasEmployee(employee));
		pkv.getSelectedActivity().addEmployee(employee);
		assertTrue(pkv.getSelectedActivity().hasEmployee(employee));
	}
	@Test
	void removeEmployeeWhichIsNotAssigned() throws Exception {
		assertThrows(Exception.class, () -> pkv.getSelectedActivity().removeEmployee(employee));
		assertFalse(pkv.getSelectedActivity().hasEmployee(employee));
	}
	@Test
	void removeEmployeeWhichHasTimeRegistred() throws Exception {
		assertFalse(pkv.getSelectedActivity().hasEmployee(employee));
		pkv.getSelectedActivity().addEmployee(employee);
		pkv.getSelectedActivity().addTime(employee, 10);
		assertTrue(pkv.getSelectedActivity().hasEmployee(employee));
		assertThrows(Exception.class, () -> pkv.getSelectedActivity().removeEmployee(employee));
	}
}
