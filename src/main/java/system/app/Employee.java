package system.app;

import java.util.*;

public class Employee {
	private String initialer;
	private List<Pomodoro> pomodoros;
	
	public Employee(String initialer) {
		this.initialer = initialer;
		this.pomodoros = new ArrayList<>();		
	}

	public void addActivity(Pomodoro pomodoro) {
		pomodoros.add(pomodoro);
	}
	
	public String getInitials() {
		return initialer;
	}
	
	public int getTimefor(String check) {
		int time = 0;
		for(Pomodoro pomodoro: pomodoros) {
			if(pomodoro.getActivity().getName().equals(check)) {
				time = pomodoro.getTime();
			}
		}
		return time;
	}
	
	public String makeRepport() {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			output += "Activity: " + pomodoro.getActivity().getName() + ", hours: " + pomodoro.getTime() + "\n";
		}
		return output;
	}
	
	public String makeRepport(String activityName) throws Exception {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity().getName().equals(activityName)) {
				output += "Activity: " + pomodoro.getActivity().getName() + ", hours: " + pomodoro.getTime() + "\n";
			}
		}
		if (output.equals("")) {
			throw new Exception("You are not assigned to this activity");
		}
		return output;
	}
}
