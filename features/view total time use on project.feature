Feature: view total time use on project
Description: A project leader requests an overview of the time left in acti-vitiesfor each employee in a project
Actors: project leader

Background: there is a project leader of a project
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	And that the project has no project leader assigned
	When an employee is set as the project leader
	Then the project has a project leader

Scenario: the project leader requests to view total time of activities of a certain project, for each employee
	Given the employee requests to view total time of "Project1"
	And the project has these activities
		| activity1 |
		| activity2 |
		| activity3 |
		| activity4 |
	And the activity "activity1" has the employee assigned
	And the activity "activity1" has 5 registred hours
	Then display a list of the work hours each employee is assigned to

Scenario: the project leader requests to view total time of activities of a certain empty project, for each employee
	Given the employee requests to view total time of "Project1"
	Then display an empty list of the work hours each employee is assigned to

Scenario: an employee who is not the project leader requests to view total time of activities of a certain project, for each employee
	Given the project has these activities
		| activity1 |
		| activity2 |
		| activity3 |
		| activity4 |
	When the employee logs out
	And a second employee logs in
	Given the employee requests to view total time of "Project1"
	Then an error message ocurres with the text "only the project can get a project report"