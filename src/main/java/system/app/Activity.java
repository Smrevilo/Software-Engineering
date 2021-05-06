package system.app;

import java.util.*;

public class Activity {


	private String name;
	private List<Pomodoro> pomodoros;
	private Calendar startDate;
	private Calendar deadline;

	public Activity(String name) {
		this.name = name;
		this.pomodoros = new ArrayList<Pomodoro>();
		this.startDate = new GregorianCalendar();
		this.deadline = new GregorianCalendar();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public void setStartDate(int startDay, int startMonth, int startYear) {
		this.startDate.set(startYear, startMonth, startDay);
	}

	public Calendar getDeadline() {
		return this.deadline;
	}

	public void setDeadline(int deadDay, int deadMonth, int deadYear) {
		this.deadline.set(deadYear, deadMonth, deadDay);
	}

	public void addEmployee(Employee employee) {
		Pomodoro pomodoro = new Pomodoro(employee, this);
		pomodoros.add(pomodoro);
		employee.addActivity(pomodoro);
	}

	public void addTime(Employee employee, int i) throws Exception {
		boolean found = false;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				if (found) {
					throw new Exception("Employee assigned twice to same activity");
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

	public int getTotalTimeForEmployee(Employee employee) {
		int output = 0;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				output += pomodoro.getTime();
			}
		}
		return output;
	}
	
	public String getRepport() {
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
		if (!hasEmployee(employee)) {
			throw new Exception("The employee is not assigned to this activity");
		}
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				if (pomodoro.getTime() != 0) {
					throw new Exception("The employee has registered hours to this activity");
				}
				pomodoros.remove(pomodoro);
				employee.removeActivity(this);
				return;
			}
		}
	}

	public boolean hasEmployee(Employee employee) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == employee) {
				return true;
			}
		}
		return false;
	}
	
}
