Feature: Edit time registration
	Description: An employee (either an ordinary employee or a project leader) 
				 registers time by providing an activity for it to be added to
	Actors: employee
				
Background: There is both an activity and a project
	Given that an employee is logged in
	And this project with these activities exits
		| New Software | 123456 | Test Developement |

Scenario: Registering time to an activity
	Given that the employee is assigned to the activity with the name "Test Developement" under the project "New Software"
	When the employee registers time to the activity with the name "Test Development" under the project "New Software"
	Then the time is registered to the activity with the name "Test Development" under the project "New Software"

Scenario: Registering time to an activity the employee is NOT assigned to
	Given that the employee is Not assigned to the activity with the name "Test Developement" under the project "New Software"
	When the employee registers time to the activity with the name "Test Development" under the project "New Software"
	Then the time is not registered to the activity with the name "Test Development" under the project "New Software"


Scenario: Registering time to sick days
	When the employee registers time to sick days
	Then the time is registered under sick days
	
Scenario: Deleting time registration for an activity
	Given that the employee is assigned to the activity with the name "Test Developement" under the project "New Software"
	And the employee has registered 5 hours to the activity with the name "Test Developement" under the project "New Software"
	When the employee deletes 3 hours from the activity with the name "Test Developement" under the project "New Software"
	Then there is only 2 hours registered for the employee on the activity with the name "Test Developement" under the project "New Software"

	
