#Feature: Edit project
#Description: An Employee edits a project
#Actors: Employee
#Background:Given the System has a project leader assigned
#
#Scenario: Add activity to project
#	Given that a project exists
#	And that the project leader has created an activity
#	When the project leader adds the activity
#	Then the project has the activity
#
#Scenario: Set leader of project
#	Given that a project exists
#	And that the project has no project leader assigned
#	When the an employee is set as the project leader
#	Then the project has an project leader