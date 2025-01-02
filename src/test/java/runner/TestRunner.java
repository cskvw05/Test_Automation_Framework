package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/",
        glue = {"stepdef","hooks","baseClass"},
       // plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"},
        tags="@api"

)
public class TestRunner  extends AbstractTestNGCucumberTests {


}
