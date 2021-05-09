Feature: Start a new project 
Description: Start a new project
Actors: Project leader

Background: An employee is logged in
	Given that an employee is logged in

Scenario: Host new project
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists

Scenario: Delete a project while being the project leader
	Given a project with the name "FailProject" has been created
	And the logged in employee is the project leader of "FailProject"
	Then a project with the name "FailProject" exists
	And the project has a project leader
	When the employee deletes the project "FailProject"
	Then the project "FailProject" no longer exists
	
Scenario: Delete a project with activities while being the project leader and fail
	Given a project with the name "FailProject" has been created
	And the logged in employee is the project leader of "FailProject"
	Then a project with the name "FailProject" exists
	And the project has a project leader
	When the logged in employee adds the activity "activity1"
	When the employee deletes the project "FailProject"
	Then an error message ocurres with the text "The project contains activities and can not be deleted"

Scenario: Delete a non-existing project
	When the employee deletes the project "NonProject"
	Then an error message ocurres with the text "A project with that name doesn't exits"

Scenario: Delete a project while NOT being the project leader
	Given a project with the name "FailProject" has been created
	Then a project with the name "FailProject" exists
	When the employee deletes the project "FailProject"
	Then an error message ocurres with the text "Only the project leaders can delete the project"

Scenario: Host new project with invalid name
	When the employee creates a project using the name ""
	Then an error message ocurres with the text "The project's name must be at least 1 charecter long"

Scenario: Host new project with already used name
	Given a project with the name "Project1" has been created
	When the employee creates a project using the name "Project1"
	Then an error message ocurres with the text "A project with the name \"Project1\" already exsits"