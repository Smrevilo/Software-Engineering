package system.app;

public class Pomodoro {
	private int time;
	private Employee employee;
	private Activity activity;
	
	public Pomodoro(Employee employee, Activity activity) {
		this.employee = employee;
		this.activity = activity;
	}
	
	public void addTime(int time) throws Exception {
		if (time < 0) {
			throw new Exception("You cannot add negative hours");		
		}
		this.time += time;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void deleteTime(int time) throws Exception {
		if (time < 0) {
			throw new Exception("You cannot delete negative hours");		
		}
		if (this.time > time) {
			this.time -= time;	
		} else {
			throw new Exception("You cannot delete more hours than you have registered");
		}
	}

}
