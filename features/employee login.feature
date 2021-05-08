Feature: login log out
	Description: An employee logins or logs out
	Actors: employee

Scenario: employee logs in
	When the employee logs in with the initials "ABCD"
	
Scenario: employee logs in with the wrong initials
	When the employee logs in with the initials "WRONG"
	Then an error message ocurres with the text "Wrong initials"
 	
Scenario: employee logs out
	When the employee logs in with the initials "ABCD"
	And  the employee logs out
	Then the employee is logged out