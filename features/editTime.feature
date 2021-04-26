Feature: Edit time registration
	Description: An employee (either an ordinary employee or a project leader) 
				 registers time by providing an activity for it to be added to
	Actors: employee
				
Background: There is both an activity and a project
	Given that an employee is logged in
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists
	And this project has this activity "Test Developement"

Scenario: Registering time to an activity
	Given that the employee is assigned to the activity with the name "Test Developement" under the project "Project1"
	When the employee registers 10 hours to the activity with the name "Test Developement" under the project "Project1"
	Then there is 10 hours registered for the employee on the activity with the name "Test Developement" under the project "Project1"

Scenario: Registering time to an activity the employee is NOT assigned to
	Given that the employee is not assigned to the activity with the name "Test Developement" under the project "Project1"
	When the employee registers 10 hours to the activity with the name "Test Developement" under the project "Project1"
	Then an error message ocurres with the text "You are not assigned to this activity"
	And the time is not registered to the activity with the name "Test Developement" under the project "Project1"

Scenario: Registering time to sick days
	When the employee registers time to sick days
	Then the time is registered under sick days
	
Scenario: Deleting time registration for an activity
	Given that the employee is assigned to the activity with the name "Test Developement" under the project "Project1"
	When the employee registers 5 hours to the activity with the name "Test Developement" under the project "Project1"
	And the employee deletes 3 hours from the activity with the name "Test Developement" under the project "Project1"
	Then there is 2 hours registered for the employee on the activity with the name "Test Developement" under the project "Project1"

Scenario: Deleting more time that there is registrated for an activity
	Given that the employee is assigned to the activity with the name "Test Developement" under the project "Project1"
	When the employee registers 3 hours to the activity with the name "Test Developement" under the project "Project1"
	And the employee deletes 4 hours from the activity with the name "Test Developement" under the project "Project1"
	Then an error message ocurres with the text "You cannot delete more hours than you have registered"
	And there is 3 hours registered for the employee on the activity with the name "Test Developement" under the project "Project1"
	
#TODO add/delete time from non-exsisting activity/project