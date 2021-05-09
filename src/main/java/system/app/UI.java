package system.app;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {

	private Scanner in;
	private PKV pkv;
	private boolean isLoggedIn = false;

	public UI(PKV pkv) {
		in = new Scanner(System.in);
		this.pkv = pkv;
	}

	// TODO:
	// Make report show info to non project leaders(very low priority)
	public void start() {
		while (true) {
			login();

			while (isLoggedIn) {
				System.out.print("Command (write \"help\" to get help): ");
				String cmd = in.nextLine().toLowerCase();
				switch (cmd) {
				case "logout":
					logOut();
					break;
				case "help":
					displayHelp();
					break;
				case "create project":
					createProject();
					break;
				case "create activity":
					createActivity();
					break;
				case "delete activity":
					deleteActivity();
					break;
				case "remove employee from activity":
					removeActivityFromEmployee();
					break;
				case "set project leader":
					setProjectLeader();
					break;
				case "make report":
					makeReport();
					break;
				case "edit time":
					editTime();
					break;
				case "add employee":
					addEmployee();
					break;
				case "set date":
					setDate();
					break;
				case "search available":
					searchAvailable();
					break;
				case "set workload":
					setWorkload();
					break;
				case "set status":
					setStatusofActivity();
					break;
				case "get time overview":
					getTimeOverview();
					break;

				default:
					System.out.println("<" + cmd + "> is not a known command");
					break;
				}
			}
		}
	}

	private void deleteActivity() {
		// TODO Auto-generated method stub

	}

	private void removeActivityFromEmployee() {
		while (true) {
			System.out.print("Name of project to remove activity to (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to set dates");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not editable");
				continue;
			}
			System.out.println("Name of employee to remove activity (q to stop): ");
			String name = in.nextLine();
			if (name.toLowerCase().equals("q")) {
				return;
			}

			System.out.print("Name of activity (q to stop): ");
			String activityName = in.nextLine();
			if (activityName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.getSelectedProject().getActivity(activityName).removeEmployee(pkv.getEmployee(name));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println(activityName+" successfully removed from "+name);
			return;
		}

	}

	private void getTimeOverview() {
		while (true) {
			try {
				System.out.println(pkv.getLoggedInAs().makeRepport());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			return;
		}
	}

	private void setStatusofActivity() {
		while (true) {
			System.out.print("Name of project to set status for (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to set status");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not editable");
				continue;
			}
			while (true) {
				System.out.print("Name of activity to set status for (q to stop): ");
				String activityName = in.nextLine();
				if (activityName.toLowerCase().equals("q")) {
					return;
				}
				try {
					pkv.setSelectedActivity(pkv.getSelectedProject().getActivity(activityName));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				try {
					System.out.println("type \"done\" or \"not done\" ");
					pkv.getSelectedActivity().setStatusOfActivity(in.nextLine().toLowerCase());
					System.out.println("Successfully set the status");
				} catch (InputMismatchException e) {
					System.out.println("error NAN");
					continue;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
		}

	}

	private void setWorkload() {
		while (true) {
			System.out.print("Name of project to set workload for (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to set workload");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not editable");
				continue;
			}
			while (true) {
				System.out.print("Name of activity to set workload for (q to stop): ");
				String activityName = in.nextLine();
				if (activityName.toLowerCase().equals("q")) {
					return;
				}
				try {
					pkv.setSelectedActivity(pkv.getSelectedProject().getActivity(activityName));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				try {
					pkv.setWorkload(in.nextInt());
					in.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("error NAN");
					continue;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
		}
	}

	private void searchAvailable() {
		while (true) {
			System.out.print("Date dd/mm/yyyy (q to stop): ");
			String date = in.nextLine();
			if (date.equals("q")) {
				return;
			}
			Scanner dateScanner = new Scanner(date.replace('/', ' '));
			try {
				int day = dateScanner.nextInt();
				int month = dateScanner.nextInt();
				int year = dateScanner.nextInt();
				pkv.checkValid(day, month, year);
				ArrayList<Employee> available = pkv.getAvailableEmployees(new GregorianCalendar(year, month, day));
				int numAvailable = available.size();
				System.out.println("On that day there " + (numAvailable == 1 ? "is" : "are") + " " + numAvailable
						+ " available employee" + (numAvailable == 1 ? "" : "s") + ":");
				for (int i = 0; i < numAvailable; i++) {
					System.out.println(available.get(i).getInitials());
				}
				return;
			} catch (InputMismatchException e) {
				System.out.println("error NAN");
				continue;
			} catch (NoSuchElementException e) {
				System.out.println("Date must contains 3 numbers");
				continue;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	private void setDate() {
		while (true) {
			System.out.print("Name of project to set date for (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to set dates");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not modifiable");
				continue;
			}
			while (true) {
				System.out.print("Name of activity to set date for (q to stop): ");
				String activityName = in.nextLine();
				if (activityName.toLowerCase().equals("q")) {
					return;
				}
				try {
					pkv.setSelectedActivity(pkv.getSelectedProject().getActivity(activityName));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}

				while (true) {
					System.out.print(
							"To set start date write \"Set Start Date\" and to set deadline write \"Set Deadline\" (q to stop): ");
					String edit = in.nextLine();
					if (edit.toLowerCase().equals("q")) {
						return;
					} else if (edit.toLowerCase().equals("set start date")) {
						try {
							setStartDate();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
					} else if (edit.toLowerCase().equals("set deadline")) {
						try {
							setDeadline();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
					}
					System.out.println("Date has been succesfully edited");
					return;
				}
			}
		}
	}

	private void setStartDate() {
		while (true) {
			System.out.print("Date dd/mm/yyyy (q to stop): ");
			String date = in.nextLine();
			if (date.equals("q")) {
				return;
			}
			Scanner dateScanner = new Scanner(date.replace('/', ' '));
			try {
				int day = dateScanner.nextInt();
				int month = dateScanner.nextInt();
				int year = dateScanner.nextInt();
				try {
					pkv.setStartDate(day, month, year);
					return;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("error NAN");
				continue;
			} catch (NoSuchElementException e) {
				System.out.println("Date must contains 3 numbers");
				continue;
			}
		}
	}

	private void setDeadline() {
		while (true) {
			System.out.print("Date dd/mm/yyyy (q to stop): ");
			String date = in.nextLine();
			if (date.equals("q")) {
				return;
			}
			Scanner dateScanner = new Scanner(date.replace('/', ' '));
			try {
				int day = dateScanner.nextInt();
				int month = dateScanner.nextInt();
				int year = dateScanner.nextInt();
				try {
					pkv.setDeadline(day, month, year);
					return;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("error NAN");
				continue;
			} catch (NoSuchElementException e) {
				System.out.println("Date must contains 3 numbers");
				continue;
			}
		}
	}

	private void addEmployee() {
		while (true) {
			System.out.println("Name of project to add employee to (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to add employees");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not editable");
				continue;
			}
			break;
		}
		while (true) {
			System.out.println("Name of activity to add employee to (q to stop): ");
			String activityName = in.nextLine();
			if (activityName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedActivity(pkv.getSelectedProject().getActivity(activityName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			break;
		}
		while (true) {
			System.out.println("Name of employee to add (q to stop): ");
			String name = in.nextLine();
			if (name.toLowerCase().equals("q")) {
				return;
			}
			try {
				Employee employee = pkv.getEmployee(name);
				pkv.addEmployeeToActivity(employee);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			return;
		}
	}

	private void editTime() {
		while (true) {
			System.out.print("Name of project to edit time for (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			while (true) {
				System.out.print("Name of activity to edit time for (q to stop): ");
				String activityName = in.nextLine();
				if (activityName.toLowerCase().equals("q")) {
					return;
				}
				try {
					pkv.setSelectedActivity(pkv.getSelectedProject().getActivity(activityName));
					if (!pkv.getSelectedActivity().isAssignedTo(pkv.getLoggedInAs())) {
						throw new Exception("You are not assigned to this activity");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}

				while (true) {
					System.out.print(
							"To add time write \"Add time\" and to delete time write \"Delete time\" (q to stop): ");
					String edit = in.nextLine();
					if (edit.toLowerCase().equals("q")) {
						return;
					} else if (edit.toLowerCase().equals("add time")) {
						try {
							addTime();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
					} else if (edit.toLowerCase().equals("delete time")) {
						try {
							deleteTime();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
					}
					System.out.println("Time has been succesfully edited");
					return;
				}
			}
		}
	}

	private void deleteTime() throws Exception {
		while (true) {
			System.out.print("How many hours do you want to delete (-1 to stop)?: ");
			try {
				int time = in.nextInt();
				in.nextLine();
				if (time == -1) {
					return;
				}
				pkv.getSelectedActivity().deleteTime(pkv.getLoggedInAs(), time);
				return;
			} catch (InputMismatchException e) {
				System.out.println("error NAN");
				continue;
			}
		}
	}

	private void addTime() throws Exception {
		while (true) {
			System.out.print("How many hours do you want to registre (-1 to stop)?: ");
			try {
				int time = in.nextInt();
				in.nextLine();
				if (time == -1) {
					return;
				}
				pkv.getSelectedActivity().addTime(pkv.getLoggedInAs(), time);
				return;
			} catch (InputMismatchException e) {
				System.out.println("error NAN");
				continue;
			}
		}
	}

	private void setProjectLeader() {
		while (true) {
			System.out.print("Name of project to set project leader (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.getProject(projectName).setLeader(pkv.getLoggedInAs());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println("Project leader set successfully");
			return;
		}
	}

	private void makeReport() {
		while (true) {
			System.out.print("Name of project to make report off (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				System.out.println(pkv.makeReportFor(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			return;
		}
	}

	private void createActivity() {
		while (true) {
			System.out.print("Name of project to add activity to (q to stop): ");
			String projectName = in.nextLine();
			if (projectName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.setSelectedProject(pkv.getProject(projectName));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (!(pkv.getSelectedProject().getLeader() == pkv.getLoggedInAs())) {
				System.out.println("You must be project leader of the project to set dates");
				continue;
			}
			if (!pkv.getSelectedProject().getEditable()) {
				System.out.println("Project is not editable");
				continue;
			}
			System.out.print("Name of activity (q to stop): ");
			String activityName = in.nextLine();
			if (activityName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.getSelectedProject().createActivty(pkv.getLoggedInAs(), activityName);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println("Activity successfully created");
			return;
		}
	}

	private void createProject() {
		while (true) {
			System.out.print("Name of project (q to stop): ");
			String name = in.nextLine();
			if (name.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.createProject(name);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println("Project successfully created");
			return;
		}
	}

	private void logOut() {
		pkv.logOut();
		isLoggedIn = false;
	}

	private void displayHelp() {
		System.out.println("Logout: Logs you out");
		System.out.println("Help: Displays this");
		System.out.println("Create Project: Creates a project");
		System.out.println("Create Activity: Creates an activity");
		System.out.println("Make Report: Prints an overview of a project");
		System.out.println("Set Project leader: Sets a project leader");
		System.out.println("Edit Time: Add og remove time to an activity");
		System.out.println("Add Employee: Adds an employee to an activity");
		System.out.println("Set Date: Set start date or deadline of an activity");
		System.out.println("Search Available: Shows who are available on a specified date");
		System.out.println("Set Workload: Sets workload for an activity");
		System.out.println("Set Status: Sets the status of an activity, ie if its done or not done");
		System.out.println("Get Time Overview: gets an overview of all activities");
	}

	private void login() {
		while (!isLoggedIn) {
			System.out.println("Write your initials to login: ");
			String initials = in.nextLine();
			try {
				pkv.login(initials);
				System.out.println("Successfully logged in as " + initials);
				isLoggedIn = true;
			} catch (Exception e) {
				System.out.println("No user with those initials exists");
			}
		}
	}
}
