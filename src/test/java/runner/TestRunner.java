package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions (
        features = {"src/test/java/feature/UI","src/test/java/feature/Mobile"},
        glue = {"utility", "hooks","stepDefinition" },
        plugin = { "json:target/cucumber-report/cucumber.json" ,"html:target/cucumber-report-html-TestRunner/index.html"},
        tags = "@MobileTestWebIOS",
        monochrome = true)
//running mvn clean verify will generate the Cucumber html report file automatically, else if u run from here it will generate json report and only index.html for html report as per plugin
// if u need customized html report, use java methods to convert json into html and do report generation at the end of all features (see after hooks and implement)
public class TestRunner {


}
