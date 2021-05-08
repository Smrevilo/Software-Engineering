Feature: set the status of the project
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

	
Scenario: Set Status to an invalid state
	When the project leader sets the status of the activity "activity1" under the project "Project1" to "WRONG"
	Then an error message ocurres with the text "Error: An activity can only either be Done or Not Done"	
	