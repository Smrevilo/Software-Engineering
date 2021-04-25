package system.app;

import java.util.*;

public class Employee {
	private String initialer;
	private List<Pomodoro> activities;
	
	public Employee(String initialer) {
		this.initialer = initialer;
		this.activities= new ArrayList<>();
				
	}

	public void addActivity(Pomodoro temp) {
		activities.add(temp);
		
	}

}
