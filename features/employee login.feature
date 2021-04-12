Feature: login log out
	Description: An employee logins or logs out
	Actors: employee

Scenario: employee logs in

	Given that an employee is logged in

 	
Scenario: employee logs out

	Given that an employee is logged in
	When the employee logs out
	Then the employee is logged out