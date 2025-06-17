package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.ExtentReportManager;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features/checkout.feature",
            glue = "stepsDefinition",
            plugin = {"pretty", "html:target/cucumber-reports.html"},
            monochrome = true
    )
    public class TestRunner3 {
        @AfterClass
        public static void tearDown() {
            ExtentReportManager.getInstance().flush();
        }
    }

