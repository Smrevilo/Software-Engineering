package system.app;

public class Pomodoro {
	private int time;
	private Employee employee;
	private Activity activity;
	
	
	public Pomodoro(Employee employee, Activity activity) {
		this.employee=employee;
		this.activity=activity;
	}

}
