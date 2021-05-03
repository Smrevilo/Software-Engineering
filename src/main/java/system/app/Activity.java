package system.app;

import java.util.*;

public class Activity {


	private String name;
	private boolean state;
	private List<Pomodoro> pomodoros;
	private Calendar startDate;
	private Calendar deadline;

	public Activity(String name) {
		this(name, false);
	}

	public Activity(String name, boolean state) {
		this.name = name;
		this.deadline = new GregorianCalendar();
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

	


	public Calendar getStartDate() {
		return this.startDate;
	}

	public void setStartDate(int startDate, int startMonth, int startYear) {
		this.startDate.set(startYear, startMonth, startDate);
	}

	public Calendar getDeadline() {
		return this.deadline;
	}

	public void setDeadline(int deadDate, int deadMonth, int deadYear) {
		this.deadline.set(deadYear, deadMonth, deadDate);
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



	@Override
	public String toString() {
		return "Activity [name=" + name + ", startDate=" + startDate.get(startDate.YEAR) +"#"+startDate.get(startDate.MONTH)+"#"+startDate.get(startDate.DAY_OF_MONTH) +"#"+ ", deadline=" + deadline.YEAR +"#"+deadline.MONTH+"#"+deadline.DAY_OF_MONTH  + "]";
	}

	public void removeEmployee(Employee employee) {
		pomodoros.remove(pomodoros.indexOf(employee));
		employee.removeActivity(this);
	}
	
}
