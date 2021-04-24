Feature: Start a new project 
Description: Start a new project
Actors: Project leader

Background: An employee is logged in
	Given that an employee is logged in

Scenario: Host new project
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists

Scenario: Host new project ERROR
	Given a project with the name "Project1" has been created
	When the employee creates a project using the name "Project1"
	Then an error containing "A project with that name already exsits" is displayed