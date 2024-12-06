package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = {"src/test/resources/features"},
        glue = "org.opencart.stepDefinitions",
        plugin = {"pretty", "junit:target/reports/cucumber-reports.xml",
                "html:target/reports/cucumber-html.html",
                "json:target/reports/cucumber-json.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class TestRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

