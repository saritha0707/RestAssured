package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {
    "pretty", // Prints readable output in the console
    "json:target/cucumber-report.json", // JSON report for integration with other tools
    "html:target/cucumber-html-reports/cucumber-html-reports.html", // HTML report
    "junit:target/cucumber-report/cucumber.xml" // JUnit XML report
}, glue = {"cost.steps"}, // Path to step definitions
        features = "src/main/feature", // Path to feature files
        tags = "not @ignore" // Exclude scenarios tagged with @ignore
)
public class RunCucumberTest {
}
