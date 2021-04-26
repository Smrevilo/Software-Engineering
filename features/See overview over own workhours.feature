Feature: See overview over own workhours

Description: An employee requests to see overview of activities
Actors: employee

Background:
	Given that an employee is logged in
	And the employee creates a project using the name "Project1"
	Given the project has these activities
		| activity1 |
		| activity2 |

Scenario: employee requests overview over one of their own activities
	And that the employee is assigned to the activity with the name "activity1" under the project "Project1"
	When the employee requests own work hours for the activity "activity1"

Scenario: employee requests overview over all of their own activities
	And that the employee is assigned to the activity with the name "activity1" under the project "Project1"
	When the employee requests own work hours for all the activities

Scenario: employee requests overview over an activity they are not assigned to
	Given that the employee is not assigned to the activity with the name "activity1" under the project "Project1"
	When the employee requests own work hours for the activity "activity1"
	Then an error message ocurres with the text "You are not assigned to this activity"