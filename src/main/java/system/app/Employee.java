package system.app;

import java.util.*;

public class Employee {
	private String initialer;
<<<<<<< HEAD
	private ArrayList<Pomodoro> pomodoros;
	private ArrayList<String> specialActivities = new ArrayList<String>(Arrays.asList(
            "Sick Days",
            "Vacation",
            "Courses"
            ));
=======
	private List<Pomodoro> pomodoros;
>>>>>>> branch 'master' of https://Maxi35@github.com/Smrevilo/Software-Engineering.git

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
<<<<<<< HEAD
		int priority = 0;
		//System.out.println(testDate.YEAR + " " + testDate.MONTH + " " + testDate.DAY_OF_MONTH);
		// If the activity is within the interval of the startdate and deadline, then increment priority 
		for (Pomodoro pomodoro : pomodoros) {
			if (specialActivities.contains(pomodoro.getActivity().getName())) {
				continue;
			}
			if (pomodoro.getActivity().getStartDate().get(Calendar.YEAR) <= testDate.get(Calendar.YEAR) && pomodoro.getActivity().getDeadline().get(Calendar.YEAR) >= testDate.get(Calendar.YEAR)) {
				if (pomodoro.getActivity().getStartDate().get(Calendar.MONTH) <= testDate.get(Calendar.MONTH) && pomodoro.getActivity().getDeadline().get(Calendar.MONTH) >= testDate.get(Calendar.MONTH)) {	
					if (pomodoro.getActivity().getStartDate().get(Calendar.DAY_OF_MONTH) <= testDate.get(Calendar.DAY_OF_MONTH) && pomodoro.getActivity().getDeadline().get(Calendar.DAY_OF_MONTH) >= testDate.get(Calendar.DAY_OF_MONTH)) {
						priority++;
					}
				}
			}
		}
		//System.out.println("initialer: "+initialer +" ||getAbiablePomodoro: "+priority);
		return priority;
	}

	public void removeActivity(Activity activity) throws Exception {
		if (!hasActivity(activity)) {
			throw new Exception("The employee is not assigned to this activity");
		}
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				if (pomodoro.getTime() != 0) {
					throw new Exception("The employee has registered hours to this activity");
				}
				pomodoros.remove(pomodoro);
				//activity.removeEmployee(employee);
				return;
			}
		}
	}

	public boolean hasActivity(Activity activity) {
		for (Pomodoro pomodoro : pomodoros) {
			if (pomodoro.getActivity() == activity) {
				return true;
			}
		}
		return false;
=======
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

>>>>>>> branch 'master' of https://Maxi35@github.com/Smrevilo/Software-Engineering.git
	}
	
}
