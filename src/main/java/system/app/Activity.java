package system.app;

import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Activity {

	private String name;
	private List<Pomodoro> pomodoros;
	private Calendar startDate;
	private Calendar deadline;
	private boolean isEditable = true;
	private int workload;
	private boolean activityisDone;
	private int maxTimePerUser = 10000;

	public Activity(String name) {
		this.name = name;
		this.pomodoros = new ArrayList<Pomodoro>();
	}
	
	public String getName() {
		return name;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public void setStartDate(int startDay, int startMonth, int startYear) throws Exception {
		if (!isEditable) {
			throw new Exception("This activity can not be modified");
		}
		Calendar temp = new GregorianCalendar();
		temp.set(startYear, startMonth, startDay);
		if (deadline != null && deadline.before(temp)) {
			throw new Exception("Startdate can not be later than deadline");
		}
		this.startDate = temp;
	}

	public Calendar getDeadline() {
		return this.deadline;
	}

	public void setDeadline(int deadDay, int deadMonth, int deadYear) throws Exception {
		if (!isEditable) {
			throw new Exception("This activity can not be modified");
		}
		Calendar temp = new GregorianCalendar();
		temp.set(deadYear, deadMonth, deadDay);
		if (startDate != null && startDate.after(temp)) {
			throw new Exception("Deadline can not be before start date");
		}
		this.deadline = temp;
	}

	public void addEmployee(Employee employee) throws Exception {
		if (isAssignedTo(employee)) {
			throw new Exception("Employee is already assigned to this activity");
		}
		Pomodoro pomodoro = new Pomodoro(employee, this);
		pomodoros.add(pomodoro);
		employee.addActivity(pomodoro);
	}

	public void addTime(Employee employee, int i) throws Exception {
		boolean found = false;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				
				if ((pomodoro.getTime() + i) >= maxTimePerUser) {
					throw new Exception("You can't register more than " + maxTimePerUser + "pomodoros to this activity");
				}
				
				pomodoro.addTime(i);
				found=true;
			}
		}
		if (!found) {
			throw new Exception("You are not assigned to this activity");
		}
	}

	public int getTotalTime() {
		int output = 0;
		for (Pomodoro pomodoro : pomodoros) {
			output += pomodoro.getTime();
		}
		return output;
	}
	
	public String getReport() {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			output += "Employee: " + pomodoro.getEmployee().getInitials() + ", hours: " + pomodoro.getTime() + "\n";
		}
		return output;
	}

	public boolean isAssignedTo(Employee check) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == check) {
				return true;
			}
		}
		return false;
	}

	public void deleteTime(Employee loggedInAs, int i) throws Exception {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == loggedInAs) {
				pomodoro.deleteTime(i);
			}
		}
	}

	public void removeEmployee(Employee employee) throws Exception {
		assertThat(employee, not(nullValue()));
		if (!hasEmployee(employee)) {
			throw new Exception("The employee is not assigned to this activity");
		}
		Pomodoro toRemove = null;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				if (pomodoro.getTime() != 0) {
					throw new Exception("The employee has registered hours to this activity");
				}
				toRemove = pomodoro;
			}
		}
		assertTrue(hasEmployee(employee));
		assertThat(toRemove.getTime(), is(0));
		pomodoros.remove(toRemove);
		employee.removeActivity(this);
		assertFalse(hasEmployee(employee));
		assertThat(toRemove.getEmployee(), is(employee));
	}

	public boolean hasEmployee(Employee employee) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				return true;
			}
		}
		return false;
	}
	
	public void setEditable(boolean editable) {
		isEditable = editable;
	}
	
	public boolean getEditable() {
		return isEditable;
	}

	public String printTime() {
		String out = "";
		if (startDate != null) {
			out += "Start: " + startDate.get(Calendar.DAY_OF_MONTH) + "/" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.YEAR) + (deadline == null ? "" : " ");
		}
		if (deadline != null) {
			out += "Deadline: " + deadline.get(Calendar.DAY_OF_MONTH) + "/" + deadline.get(Calendar.MONTH) + "/" + deadline.get(Calendar.YEAR);
		}

		return out;
	}

	public void setWorkload(int workload) {
		this.workload = workload;
	}

	public int getWorkload() {
		return workload;
	}

	public void setStatusOfActivity(String status) throws Exception {
		if (status.toLowerCase().equals("done")) {
			this.activityisDone = true;
			
		} else if (status.toLowerCase().equals("not done")) {
			this.activityisDone = false;
		} else {
			throw new Exception("Error: An activity can only either be Done or Not Done");
		}
		
	}
	
	public List<Pomodoro> getPomodoros(){
		return pomodoros;
	}

	public boolean getStatusOfActivity() {
		return this.activityisDone;
		
	}
}
