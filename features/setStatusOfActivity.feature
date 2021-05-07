Feature: set the status of the projectt
Description: A project leader edits the statuts for an activity
Actors: Employee

Background: The system has a project
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	And the logged in employee is the project leader of "Project1"
	And the project leader adds the activity "activity1"

Scenario: Set Status to Done
	When the project leader sets the status of the activity "activity1" under the project "Project1" to "Done"
	Then the activity "activity1" under the project "Project1" has the status "Done"
	
Scenario: Set Status to Not Done
	When the project leader sets the status of the activity "activity1" under the project "Project1" to "Not Done"
	Then the activity "activity1" under the project "Project1" has the status "Not Done"	
