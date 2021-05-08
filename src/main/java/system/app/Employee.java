package system.app;

import java.util.*;

public class Employee {
	private String initialer;
	private ArrayList<Pomodoro> pomodoros;
	private ArrayList<String> specialActivities = new ArrayList<String>(Arrays.asList(
            "Sick Days",
            "Vacation",
            "Courses"
            ));

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

	public int getTimefor(String activityName) {
		int time = 0;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity().getName().equals(activityName)) {
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
		int numAvailable = 0;
		
		for (Pomodoro pomodoro : pomodoros) {
			if (specialActivities.contains(pomodoro.getActivity().getName())) {
				continue;
			}
			if (pomodoro.getActivity().getStartDate().get(Calendar.YEAR) <= testDate.get(Calendar.YEAR) && pomodoro.getActivity().getDeadline().get(Calendar.YEAR) >= testDate.get(Calendar.YEAR)) {
				if (pomodoro.getActivity().getStartDate().get(Calendar.MONTH) <= testDate.get(Calendar.MONTH) && pomodoro.getActivity().getDeadline().get(Calendar.MONTH) >= testDate.get(Calendar.MONTH)) {	
					if (pomodoro.getActivity().getStartDate().get(Calendar.DAY_OF_MONTH) <= testDate.get(Calendar.DAY_OF_MONTH) && pomodoro.getActivity().getDeadline().get(Calendar.DAY_OF_MONTH) >= testDate.get(Calendar.DAY_OF_MONTH)) {
						numAvailable++;
					}
				}
			}
		}

		return numAvailable;
	}

	public void removeActivity(Activity activity) throws Exception {
		Pomodoro toRemove = null;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				toRemove = pomodoro;
			}
		}
		pomodoros.remove(toRemove);
	}

	public boolean hasActivity(Activity activity) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				return true;
			}
		}
		return false;
	}
	
}
