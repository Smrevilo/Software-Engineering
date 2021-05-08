Feature: Edit activity
Description: An Employee edits an activity
Actors: Employee

Background: The system has a project
	Given that an employee is logged in
	When the employee creates a project using the name "Project1"
	Then a project with the name "Project1" exists
	Given the logged in employee is the project leader of "Project1"
	When the logged in employee adds the activity "activity1"
	Then the project has the activity "activity1"

Scenario: Project leader adds employee to activity
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is assigned to the activity "activity1" under project "Project1"

Scenario: Project leader adds employee to activity twice
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is assigned to the activity "activity1" under project "Project1"
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then an error message ocurres with the text "Employee is already assigned to this activity"

Scenario: Non project leader adds employee to activity
	Given the logged in employee is not the project leader of "Project1"
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then an error message ocurres with the text "Only the project leader can add the employee to the activity"

Scenario: Project leader removes an employee from an activity
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is assigned to the activity "activity1" under project "Project1"
	When the employee removes the other employee with the initials "ABCE" from the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is not assigned to the activity "activity1" under project "Project1"

Scenario: Project leader adds non-existing employee to activity
	When the employee adds another employee with the initials "WRONG" to the activity "activity1" under project "Project1"
	Then an error message ocurres with the text 'An employee with the initials "WRONG" does not exsit'

Scenario: Project leader removes an employee from an activity which they are not assigned to
	When the employee removes the other employee with the initials "ABCE" from the activity "activity1" under project "Project1"
	Then an error message ocurres with the text "The employee is not assigned to this activity"

Scenario: Project leader removes an employee from an activity which they already have registered time to
	When the employee adds another employee with the initials "ABCE" to the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is assigned to the activity "activity1" under project "Project1"
	When the employee logs out
	And a second employee logs in
	When the employee registers 10 hours to the activity with the name "activity1" under the project "Project1"
	Then there is 10 hours registered for the employee on the activity with the name "activity1" under the project "Project1"
	When the employee logs out
	And that an employee is logged in
	When the employee removes the other employee with the initials "ABCE" from the activity "activity1" under project "Project1"
	Then the employee with the initials "ABCE" is assigned to the activity "activity1" under project "Project1"
	And an error message ocurres with the text "The employee has registered hours to this activity"