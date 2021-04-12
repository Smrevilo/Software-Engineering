This project can be used as a starting project for your own projects.

It contains all the necessary libraries to run Cucumber tests, JUnit 5 tests, and JUnit 4 tests. In addition, it contains the reference to the Mockito libraries.

It is a good idea to change the name of the project. Don't forget to also change the name in the `pom.xml` file. 

If you use a different version than Java 11, then change the `maven.compiler.source` and `maven.compiler.target` properties in the `pom.xml` file. This should only be needed if you want to use Java 8. The setting Java 11 is compatible with all installed JDK's of version 11 or higher.

It can be run through Maven, e.g., `mvn clean test`, Eclipse (run as JUnit test), and ItelliJ. 

In case of Eclipse, if the run configuratiom for the test is set to JUnit 4, all the Cucumber tests and all the JUnit 4 tests are run, but not the JUnit 5 tests. If the setting is set to JUnit 5, then all the tests are run.