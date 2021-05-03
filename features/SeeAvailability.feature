Feature: See availability of co-workers 
	Description: A project leader or an employee wants to see who is available at a specific date
	
Actors: employee/project leader

Background: 
	Given that an employee is logged in 
	When the employee creates a project using the name "Project1" 
	And an employee is set as the project leader
	Then the project has a project leader
	And The threshold for the amount of activities that decides if a empolyee is available is 10
	And every employee has theese activities assigned, with the start date 20 05 2021 , and the deadline 27 05 2021, for the project "Project1" 
		| activity0 |
		| activity1 | 
		| activity2 | 
		| activity3 |
		| activity4 | 
		| activity5 | 
		| activity6 |
		| activity7 | 
		| activity8 | 
		| activity9 | 
		| activity10 |
	
Scenario: Looking for availability at a specific date
	When the logged in employee looks for availability at the date 25 05 2021
	Then display a list of 0 employee that are available at that date
	When the logged in employee looks for availability at the date 19 05 21
	Then display a list of 29 employee that are available at that date

	