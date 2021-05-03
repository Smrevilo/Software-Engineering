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
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity().getName().equals(check)) {
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

	public int getAvailablePomodoro(GregorianCalendar testDate) {
		int i = 0;
		// If the activity is within the interval of the startdate and deadline, then increment business 
		for (Pomodoro pomodoro : pomodoros) {
			
			if ((pomodoro.getActivity().getStartDate().YEAR >= testDate.YEAR && pomodoro.getActivity().getDeadline().YEAR <= testDate.YEAR)) {
				if ((pomodoro.getActivity().getStartDate().MONTH >= testDate.MONTH && pomodoro.getActivity().getDeadline().MONTH <= testDate.MONTH)) {	
					if ((pomodoro.getActivity().getStartDate().DAY_OF_MONTH >= testDate.DAY_OF_MONTH && pomodoro.getActivity().getDeadline().DAY_OF_MONTH <= testDate.DAY_OF_MONTH)) {
						i++;
					}
				}
			}
		}
		System.out.println("initialer: "+initialer +" ||getAbiablePomodoro: "+i);
		return i;
	}

	@Override
	public String toString() {
		return "Employee [initialer=" + initialer + "]";
	}

	public void removeActivity(Activity activity) {
		pomodoros.remove(pomodoros.indexOf(activity));

	}
	
}
