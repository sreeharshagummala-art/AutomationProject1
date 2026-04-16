package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (features = "src/test/java/features/GoogleAPI.feature",
                plugin = "json:target/jsonReports/cucumber-report.json",
                glue = {"steps"})
public class TestRunner {

    //Above is the code for runner using Junit. Running this file will execute the feature file.
}
