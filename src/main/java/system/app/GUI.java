package system.app;

import java.util.Scanner;

public class GUI {

	private Scanner in;
	private PKV pkv;
	private boolean isLoggedIn = false;
	
	public GUI(PKV pkv) {
		in = new Scanner(System.in);
		this.pkv = pkv;
	}
	
	public void start() {
		while (true) {
			login();
		
			while (isLoggedIn) {
				System.out.print("Command (write \"help\" to get help): ");
				switch (in.nextLine().toLowerCase()) {
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
					case "set project leader":
						setProjectLeader();
						break;
					case "make report":
						makeReport();
						break;
				}
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
			System.out.print("Name of activity (q to stop): ");
			String activityName = in.nextLine();
			if (activityName.toLowerCase().equals("q")) {
				return;
			}
			try {
				pkv.getSelectedProject().createActivty(activityName);
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
	}

	private void login() {
		while (!isLoggedIn) {
			System.out.println("Write your initials to login: ");
			String initials = in.nextLine();
			boolean success = pkv.login(initials);
			if (success) {
				System.out.println("Successfully logged in as " + initials);
				isLoggedIn = true;
			} else {
				System.out.println("No user with those initials exists");
			}
		}
	}
}
