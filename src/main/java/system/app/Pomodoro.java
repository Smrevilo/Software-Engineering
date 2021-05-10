// Mads s204170
package system.app;

public class Pomodoro {
	private int time;
	private Employee employee;
	private Activity activity;
	
	// Mads s204170
	public Pomodoro(Employee employee, Activity activity) {
		this.employee = employee;
		this.activity = activity;
	}
	
	// Mads s204170
	public void addTime(int time) throws Exception {
		if (time < 0) {
			throw new Exception("You cannot add negative hours");		
		}
		this.time += time;
	}
	
	// Mads s204170
	public int getTime() {
		return this.time;
	}
	
	// Mads s204170
	public Employee getEmployee() {
		return employee;
	}
	
	// Mads s204170
	public Activity getActivity() {
		return activity;
	}

	// Mads s204170
	public void deleteTime(int time) throws Exception {
		if (time < 0) {
			throw new Exception("You cannot delete negative hours");		
		}
		if (this.time >= time) {
			this.time -= time;	
		} else {
			throw new Exception("You cannot delete more hours than you have registered");
		}
	}

}
