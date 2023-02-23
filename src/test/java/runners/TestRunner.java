package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features", glue = "steps", publish = true,
        plugin = {"pretty", "html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json"}, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    //(parallel=true),tags = "@smoke"
    @DataProvider
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
