//Viktor s204166
package system.app;

import java.util.*;


public class Employee {
	private String initialer;
	private ArrayList<Pomodoro> pomodoros;
	private ArrayList<String> specialActivities = new ArrayList<String>(
			Arrays.asList("Sick Days", "Vacation", "Courses"));

	//Viktor s204166
	public Employee(String initialer) {
		this.initialer = initialer;
		this.pomodoros = new ArrayList<>();
	}

	//Viktor s204166
	public void addActivity(Pomodoro pomodoro) {
		pomodoros.add(pomodoro);
	}

	//Viktor s204166
	public String getInitials() {
		return initialer;
	}

	//Viktor s204166
	public int getTimefor(String activityName) {
		int time = 0;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity().getName().equals(activityName)) {
				time = pomodoro.getTime();
			}
		}
		return time;
	}

	//Viktor s204166
	public String makeRepport() {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			output += "Activity: " + pomodoro.getActivity().getName() + ", hours: " + ((float)pomodoro.getTime())/2.0 + "\n";
		}
		return output;
	}

	//Viktor s204166
	public String makeRepport(String activityName) throws Exception {
		String output = "";
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity().getName().equals(activityName)) {
				output += "Activity: " + pomodoro.getActivity().getName() + ", hours: " + ((float)pomodoro.getTime())/2.0 + "\n";
			}
		}
		if (output.equals("")) {
			throw new Exception("You are not assigned to this activity");
		}
		return output;
	}

	//Oliver s204182
	public int getAvailablePomodoro(GregorianCalendar testDate) {
		assert(testDate!=null);
		ArrayList<String> check = new ArrayList<String>(Arrays.asList("Sick Days", "Vacation", "Courses"));
		assert(specialActivities.containsAll(check));
		assert(pomodoros.size()>=3);
		
		int activActivities = 0;

		for (Pomodoro pomodoro : pomodoros) {
			assert(pomodoro.getActivity()!=null);
			if (specialActivities.contains(pomodoro.getActivity().getName())) {
				continue;
			}
			try {
				if (pomodoro.getActivity().getStartDate().before(testDate)
						&& pomodoro.getActivity().getDeadline().after(testDate)) {
					activActivities++;
				}
			} catch (NullPointerException e) {
				activActivities++;
			}

		}
		assert(activActivities>=0);
		return activActivities;

	}

	//Viktor s204166
	public void removeActivity(Activity activity) throws Exception {
		Pomodoro toRemove = null;
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				toRemove = pomodoro;
			}
		}
		pomodoros.remove(toRemove);
	}

	//Viktor s204166
	public boolean hasActivity(Activity activity) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				return true;
			}
		}
		return false;
	}

}
