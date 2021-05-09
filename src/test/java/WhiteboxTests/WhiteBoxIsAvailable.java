package WhiteboxTests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import system.app.Activity;
import system.app.Employee;
import system.app.PKV;
import system.app.Pomodoro;
import system.app.Project;

class WhiteBoxIsAvailable {
	
//	public int getAvailablePomodoro(GregorianCalendar testDate) {
//		assert(testDate!=null);
//		ArrayList<String> check = new ArrayList<String>(Arrays.asList("Sick Days", "Vacation", "Courses"));
//		assert(specialActivities.containsAll(check));
//		assert(pomodoros.size()>=3);
//		
//		int activActivities = 0;
//
//		for (Pomodoro pomodoro : pomodoros) {
//			assert(pomodoro.getActivity()!=null);
//			if (specialActivities.contains(pomodoro.getActivity().getName())) {
//				continue;
//			}
//			try {
//				if (pomodoro.getActivity().getStartDate().before(testDate)
//						&& pomodoro.getActivity().getDeadline().after(testDate)) {
//					activActivities++;
//				}
//			} catch (NullPointerException e) {
//				activActivities++;
//			}
//
//		}
//		assert(activActivities>=0);
//		return activActivities;
//
//	}
	
	
	PKV pkvSystem;
	Employee eUser;
	Project selectedProject;
	Activity selectedActivty;
	
	public WhiteBoxIsAvailable() throws Exception {
		pkvSystem = new PKV();
		pkvSystem.login("ABCD");
		eUser = pkvSystem.getEmployee("ABCD");
		pkvSystem.createProject("project1");
		pkvSystem.setSelectedProject(pkvSystem.getProject("project1"));
		pkvSystem.getSelectedProject().setLeader(pkvSystem.getLoggedInAs());
		String[] activityNames = {"activity1", "activity2","activity3","activity4","activity5","activity6","activity7","activity8","activity9","activity10","activity11"};
		for (String activityName : activityNames) {
			pkvSystem.getSelectedProject().createActivty(pkvSystem.getLoggedInAs(), activityName);
			pkvSystem.setSelectedActivity(pkvSystem.getSelectedProject().getActivity(activityName));
			pkvSystem.setStartDate(15, 05, 2021);
			pkvSystem.setDeadline(30, 05, 2021);
			
		}
		ArrayList<Activity> activityList = pkvSystem.getSelectedProject().getActivities();
		for (Activity activity : activityList) {
			activity.addEmployee(pkvSystem.getLoggedInAs());
			
		}
	}
	
	@Test
	void StartDeadBefore() throws Exception{
		GregorianCalendar date= new GregorianCalendar();
		date.set(2022, 9, 01);
		assertThat(pkvSystem.getSelectedProject().getActivities().size(), is(11));
		assertDoesNotThrow(() -> pkvSystem.checkValid(01, 9, 2022));
	
		Employee test= pkvSystem.getLoggedInAs();
		assertThat(test.getAvailablePomodoro(date), is(0));
	}
	
	@Test
	void StartDeadAfter() {
		GregorianCalendar date= new GregorianCalendar();
		date.set(2007, 29, 12);
		assertThat(pkvSystem.getSelectedProject().getActivities().size(), is(11));
		assertDoesNotThrow(() -> pkvSystem.checkValid(01, 9, 2022));
	
		Employee test= pkvSystem.getLoggedInAs();
		assertThat(test.getAvailablePomodoro(date), is(0));
	}
	
	@Test
	void StartBeforeDeadAfter() {
		GregorianCalendar date= new GregorianCalendar();
		date.set(2021, 5, 23);
		assertThat(pkvSystem.getSelectedProject().getActivities().size(), is(11));
		assertDoesNotThrow(() -> pkvSystem.checkValid(01, 9, 2022));
	
		Employee test= pkvSystem.getLoggedInAs();
		assertThat(test.getAvailablePomodoro(date), is(11));
	}
	
	@Test
	void OnlyRegular() throws Exception {
		pkvSystem.login("ABCE");
		GregorianCalendar date= new GregorianCalendar();
		date.set(2022, 9, 01);
		assertThat(pkvSystem.getSelectedProject().getActivities().size(), is(11));
		assertDoesNotThrow(() -> pkvSystem.checkValid(01, 9, 2022));
	
		Employee test= pkvSystem.getLoggedInAs();
		assertThat(test.getAvailablePomodoro(date), is(0));
	}
		
}
