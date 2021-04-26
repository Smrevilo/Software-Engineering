Feature: Set startdate and deadline for an activity
Description: A project leader wants to set a deadline and a startdate
Actors: project leader

Background: a project leader is logged in
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	When an employee is set as the project leader
	Then the project has a project leader

Scenario: A project leader sets a startdate for an activity
	Given the project has these activities
		| activity1 |
	When the employee sets the startdate of the activity "activity1" under the project "Project1" to the 20 - 05 - 21
	Then the acitivity "activity1" under the project "Project1" has a startdate that says 20 - 05 - 21

