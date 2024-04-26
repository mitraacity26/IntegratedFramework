package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utility.BrowserDriver;

public class AfterHooks extends BrowserDriver {

    @After("@AfterHook")
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Embed screenshot in Cucumber report
            scenario.attach(screenshot, "image/png", "Reports");
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void afterAllFeatures() {
        // Code to be executed after all features are executed
        System.out.println("All features executed. Running clean-up tasks...");
        // Your clean-up tasks or any other actions you want to perform
    }
}
