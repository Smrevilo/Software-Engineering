Feature: Start a new project 
Description: Start a new project
Actors: Project leader

Scenario: Host new project
Given that the Project leader have a project to start
And the project have a name not already used by another project
When the Project leader create the project using the name
Then the Project leader can access a project called that name

Scenario: Host new project ERROR
Given that the Project leader have a project to start
And the project have a name already used by another project
When the Project leader create the project using the name
Then the Project leader request is denied
And an error containing ”That name is occupied”is displayed