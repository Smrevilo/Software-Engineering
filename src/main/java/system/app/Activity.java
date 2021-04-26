package system.app;

import java.util.*;

public class Activity {

	private String name;
	private int workLoad;
	private int deadline;
	private boolean state;
	private List<Pomodoro> pomodoros;
	private Calendar startDate;

	public Activity(String name) {
		this(name, 0, 0, 0, false);
	}

	public Activity(String name, int workLoad, int startDate, int deadline, boolean state) {
		this.name = name;
		this.workLoad = workLoad;
		this.deadline = deadline;
		this.state = state;
		this.pomodoros = new ArrayList<Pomodoro>();
		this.startDate = new GregorianCalendar();
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


	public int[] getStartDate() {
		int[] output=new int[3];
		output[0]=startDate.get(startDate.DAY_OF_MONTH);
		output[1]=startDate.get(startDate.MONTH);
		output[2]=startDate.get(startDate.YEAR);		
		return output;
	}

	public void setStartDate(int startDate, int startMonth, int startYear) {
		System.out.println(startDate);
		System.out.println(startMonth);
		System.out.println(startYear);
		this.startDate.set(startYear, startMonth, startDate);
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
}
