package system.test;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/* Important: 
for Cucumber tests to be recognized by Maven, the class name has to have
either the word Test in the beginning or at the end. 
For example, the class name CucumberTests (Test with an s) will be ignored by Maven.
*/

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "summary", "html:target/cucumber/wikipedia.html"}
		 ,features={"features"}
		 ,publish= false
		 )
public class CucumberTest {
}
