package system.app;

public class Pomodoro {
	private int time;
	private Employee employee;
	private Activity activity;
	
	
	public Pomodoro(Employee employee, Activity activity) {
		this.employee=employee;
		this.activity=activity;
	}
	
	
	public void addTime(int time) {
		this.time+=time;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public String getActivity() {
		return activity.getName();
	}

}
