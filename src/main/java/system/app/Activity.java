package system.app;

import java.util.*;

public class Activity {


	private String name;
	private List<Pomodoro> pomodoros;
	private Calendar startDate;
	private Calendar deadline;
	private boolean isEditable = true;
	private int workload;

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
		this.startDate = new GregorianCalendar();
		this.startDate.set(startYear, startMonth, startDay);
	}

	public Calendar getDeadline() {
		return this.deadline;
	}

	public void setDeadline(int deadDay, int deadMonth, int deadYear) throws Exception {
		if (!isEditable) {
			throw new Exception("This activity can not be modified");
		}
		this.deadline = new GregorianCalendar();
		this.deadline.set(deadYear, deadMonth, deadDay);
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
		pomodoros.remove(toRemove);
		employee.removeActivity(this);
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
}
