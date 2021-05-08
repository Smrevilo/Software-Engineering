Feature: Edit project
Description: An Employee edits a project
Actors: Employee

Background: The system has a project
	Given that an employee is logged in
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists

Scenario: Add activity to project
	Given the logged in employee is the project leader of "Project1"
	When the logged in employee adds the activity "activity1"
	Then the project has the activity "activity1"

Scenario: Adding an activity with the same name twice to project
	Given the logged in employee is the project leader of "Project1"
	When the logged in employee adds the activity "activity1"
	Then the project has the activity "activity1"
	When the logged in employee adds the activity "activity1"
	Then an error message ocurres with the text "An activity with the name \"activity1\" already exsits"

Scenario: Add activity to project of which the employee is not a project leader
	When the logged in employee adds the activity "activity1"
	Then an error message ocurres with the text "Only the project leader can create an new activity"

Scenario: Add activity to project to non-editable project
	Given the logged in employee is the project leader of "ABCD"
	When the logged in employee adds the activity "activity1"
	Then an error message ocurres with the text "This project can not be modified"

Scenario: Add activity with invalid name to project
	Given the logged in employee is the project leader of "Project1"
	When the logged in employee adds the activity ""
	Then an error message ocurres with the text "The activity's name must be at least 1 charecter long"

Scenario: Set project leader of non-existing project
	When an employee is set as the project leader of the project "NonProject"
	Then an error message ocurres with the text "A project with that name doesn't exits"

Scenario: Set leader of project
	Given that the project has no project leader assigned
	When an employee is set as the project leader of the project "Project1"
	Then the project has a project leader