package system.app;

import java.util.*;

public class Activity {

	private String name;
	private int workLoad;
	private int startDate;
	private int deadline;
	private boolean state;
	private List<Pomodoro> pomodoro;
	
	public Activity(String name) {
		this(name, 0, 0, 0, false);
	}
	
	public Activity(String name, int workLoad, int startDate, int deadline, boolean state) {
		this.name = name;
		this.workLoad = workLoad;
		this.startDate = startDate;
		this.deadline = deadline;
		this.state = state;
		this.pomodoro= new ArrayList<Pomodoro>();
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
		Pomodoro temp= new Pomodoro(employee, this);
		pomodoro.add(temp);
		employee.addActivity(temp);
	}

	public void addtime(Employee loggedInAs, int i) {
		for(Pomodoro employee: pomodoro) {
			if(employee.getEmployee()==loggedInAs) {
				employee.addTime(i);
			}
		}
		
	}
	
	public int getTotalTime() {
		int output=0;
		for(Pomodoro time: pomodoro) {
			output+=time.getTime();
		}
		return output;
	}

	public boolean isAssignedTo(Employee check) {
		for(Pomodoro employee: pomodoro) {
			if(employee.getEmployee()==check) {
				return true;
			}
		}
		return false;
	}
}







