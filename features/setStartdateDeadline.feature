Feature: Set startdate and deadline for an activity
Description: A project leader wants to set a deadline and a startdate
Actors: project leader

Background: a project leader is logged in
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	When an employee is set as the project leader of the project "Project1"
	Then the project has a project leader

Scenario: A project leader sets a startdate for an activity
	Given the project has these activities
		| activity1 |
	When the employee sets the startdate of the activity "activity1" under the project "Project1" to the 20 - 05 - 2021
	Then the acitivity "activity1" under the project "Project1" has a startdate that says 20 - 05 - 2021

Scenario: A project leader sets a deadline for an activity
	Given the project has these activities
		| activity1 |
	When the employee sets the deadline of the activity "activity1" under the project "Project1" to the 30 - 05 - 2021
	Then the acitivity "activity1" under the project "Project1" has a deadline that says 30 - 05 - 2021
	
Scenario: A project leader sets a invalid startdate for an activity
	Given the project has these activities
		| activity1 |
	When the employee sets the startdate of the activity "activity1" under the project "Project1" to the 32 - 05 - 2021
	Then an error message ocurres with the text "Must be a valid date"
	
Scenario: A project leader sets a invalid deadline for an activity
	Given the project has these activities
		| activity1 |
	When the employee sets the deadline of the activity "activity1" under the project "Project1" to the 14 - 13 - 2021
	Then an error message ocurres with the text "Must be a valid date"
	
Scenario: A employee sets deadline of project which they are not a project leader off
	Given the project has these activities
		| activity1 |
	And that the project has no project leader assigned
	When the employee sets the deadline of the activity "activity1" under the project "Project1" to the 14 - 13 - 2021
	Then an error message ocurres with the text "Only the project leader can set a deadline"
	
Scenario: A employee sets startDate of project which they are not a project leader off
	Given the project has these activities
		| activity1 |
	And that the project has no project leader assigned
	When the employee sets the startdate of the activity "activity1" under the project "Project1" to the 14 - 13 - 2021
	Then an error message ocurres with the text "Only the project leader can set a startdate"
	
Scenario: A employee sets startDate of an activity which is not editable
	When the employee sets the startdate of the activity "Sick Days" under the project "ABCD" to the 14 - 5 - 2021
	Then an error message ocurres with the text "This activity can not be modified"
	
Scenario: A employee sets deadline of an activity which is not editable
	When the employee sets the deadline of the activity "Sick Days" under the project "ABCD" to the 20 - 5 - 2021
	Then an error message ocurres with the text "This activity can not be modified"