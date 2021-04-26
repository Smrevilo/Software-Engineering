package system.app;

import java.util.*;

public class Activity {

	private String name;
	private int workLoad;
	private int startDate;
	private int deadline;
	private boolean state;
	private List<Pomodoro> pomodoros;

	public Activity(String name) {
		this(name, 0, 0, 0, false);
	}

	public Activity(String name, int workLoad, int startDate, int deadline, boolean state) {
		this.name = name;
		this.workLoad = workLoad;
		this.startDate = startDate;
		this.deadline = deadline;
		this.state = state;
		this.pomodoros = new ArrayList<Pomodoro>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(int workLoad) {
		this.workLoad = workLoad;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void addEmployee(Employee employee) {
		Pomodoro temp = new Pomodoro(employee, this);
		pomodoros.add(temp);
		employee.addActivity(temp);
	}

	public void addTime(Employee loggedInAs, int i) throws Exception {
		boolean found=false;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getEmployee() == loggedInAs) {
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
}
