package test_runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "step_definitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/BookingPage-report",
                "json:target/cucumber-reports/cucumberTestReports.json",
                "rerun:target/rerun.txt"
        },
        tags = "@ff",
        dryRun = false
)
public class BookingPage_Runner {
        // This is the test runner class to run the Cucumber tests.
}
