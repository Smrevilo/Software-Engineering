Feature: Edit workload
Description: A project leader edits workload for an activity
Actors: Employee

Background: The system has a project
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	And the logged in employee is the project leader of "Project1"
	And the project leader adds the activity "activity1"

Scenario: Set workload
	When the project leader sets the workload of the activity "activity1" under the project "Project1" to 200
	Then the activity "activity1" under the project "Project1" has the workload set to 200