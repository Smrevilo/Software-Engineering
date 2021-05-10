// Mads s204170
package system.app;

import static org.junit.Assert.assertFalse;

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
	
	// Mads s204170
	public Activity(String name) {
		this.name = name;
		this.pomodoros = new ArrayList<Pomodoro>();
	}
	
	// Mads s204170
	public String getName() {
		return name;
	}
	
	// Mads s204170
	public Calendar getStartDate() {
		return this.startDate;
	}
	
	// Mads s204170
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

	// Mads s204170
	public Calendar getDeadline() {
		return this.deadline;
	}

	// Mads s204170
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
	
	// Mads s204170
	public void addEmployee(Employee employee) throws Exception {
		if (isAssignedTo(employee)) {
			throw new Exception("Employee is already assigned to this activity");
		}
		Pomodoro pomodoro = new Pomodoro(employee, this);
		pomodoros.add(pomodoro);
		employee.addActivity(pomodoro);
	}

	// Mads s204170
	public void addTime(Employee employee, int i) throws Exception {
		assert(employee != null);
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
		assert(true);
	}
	
	// Mads s204170
	public int getTotalTime() {
		int output = 0;
		for (Pomodoro pomodoro : pomodoros) {
			output += pomodoro.getTime();
		}
		return output;
	}
	
	// Mads s204170
	public String getReport() {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			output += "\tEmployee: " + pomodoro.getEmployee().getInitials() + ", hours: " + ((float)pomodoro.getTime())/2.0 + "\n";
		}
		return output;
	}

	// Mads s204170
	public boolean isAssignedTo(Employee check) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == check) {
				return true;
			}
		}
		return false;
	}

	// Mads s204170
	public void deleteTime(Employee loggedInAs, int i) throws Exception {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == loggedInAs) {
				pomodoro.deleteTime(i);
			}
		}
	}

	//Maximilian s204178
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

	// Mads s204170
	public boolean hasEmployee(Employee employee) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				return true;
			}
		}
		return false;
	}
	
	// Mads s204170
	public void setEditable(boolean editable) {
		isEditable = editable;
	}
	
	// Mads s204170
	public boolean getEditable() {
		return isEditable;
	}

	// Mads s204170
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

	// Mads s204170
	public void setWorkload(int workload) {
		this.workload = workload;
	}

	// Mads s204170
	public int getWorkload() {
		return workload;
	}

	// Mads s204170
	public void setStatusOfActivity(String status) throws Exception {
		if (status.toLowerCase().equals("done")) {
			this.activityisDone = true;
			
		} else if (status.toLowerCase().equals("not done")) {
			this.activityisDone = false;
		} else {
			throw new Exception("Error: An activity can only either be Done or Not Done");
		}
		
	}
	
	// Mads s204170
	public List<Pomodoro> getPomodoros(){
		return pomodoros;
	}

	// Mads s204170
	public boolean getStatusOfActivity() {
		return this.activityisDone;
		
	}
}
