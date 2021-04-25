Feature: Edit project
Description: An Employee edits a project
Actors: Employee

Background: The system has a project
	Given that an employee is logged in
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists

Scenario: Add activity to project
	Given the logged in employee is the project leader of "Project1"
	When the project leader adds the activity "activity1"
	Then the project has the activity "activity1"

Scenario: Set leader of project
	Given that the project has no project leader assigned
	When an employee is set as the project leader
	Then the project has a project leader