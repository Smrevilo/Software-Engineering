Feature: view total time use on project

description: A project leader requests an overview of the time left in acti-vitiesfor each employee in a project
Actors: project leader
Background:the project leader is part of a project, and he/she is the project leader 
of that project. The project leader is signed in

Scenario: the project leader requests to view total time of activities of acertain project, for each employee
Given the project leader is requesting to view total time of a project
When the project leader request to view the total amount of work hours
Then show the project leader a list of the work hours each employee isassigned to