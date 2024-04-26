package utility;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class Common extends BrowserDriver {

    public static Scenario message;

    @Given("User OPEN with {string}")
    public static void userOpenURL(String Url) throws Exception {
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");
        JSONObject jsonObject = new JSONObject(jsonString);

        if (Url.startsWith("<") && Url.endsWith(">")) {

            Url = Url.substring(1, Url.length() - 1);
            if (jsonObject.has(Url)) {
                Url = jsonObject.getString(Url);
            } else {
                throw new JSONException("Variable not found in JSON: " + Url);
            }
        }

        driver.get(Url);
    }

    @Given("User PAUSE_IN_SEC with {string}")
    public static void userPausesInSeconds(String waitInSec) throws Exception {
        int time = 0;

        String jsonString = readFileAsString("src/test/java/ObjectRepo/object.json");
        JSONObject jsonObject = new JSONObject(jsonString);
        if (waitInSec.startsWith("<") && waitInSec.endsWith(">")) {
            waitInSec = waitInSec.substring(1, waitInSec.length() - 1);
            if (jsonObject.has(waitInSec)) {
                time = Integer.parseInt(jsonObject.getString(waitInSec));
                // Write code here to pause for the specified number of seconds
                System.out.println("Pausing for " + time + " seconds");
                try {
                    Thread.sleep(time * 1000); // Pause for 'seconds' seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                throw new JSONException("Variable not found in JSON: " + waitInSec);
            }
        } else {
            // Write code here to pause for the specified number of seconds
            System.out.println("Pausing for " + waitInSec + " seconds");
            try {
                Thread.sleep(Integer.parseInt(waitInSec) * 1000); // Pause for 'seconds' seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @And("User CLICK on {string}")
    public static void userClicksOnLocator(String locator) throws Exception {
        // Click on the element with the locator

        String jsonString = readFileAsString("src/test/java/ObjectRepo/object.json");
        JSONObject jsonObject = new JSONObject(jsonString);
        if (locator.startsWith("<") && locator.endsWith(">")) {
            locator = locator.substring(1, locator.length() - 1);
            if (jsonObject.has(locator)) {
                locator = jsonObject.getString(locator);
            } else {
                throw new JSONException("Variable not found in JSON: " + locator);
            }
        }
        WebElement element = driver.findElement(By.xpath(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        //driver.findElement(By.xpath(locator)).click();
       // MobileElement elementToClick = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='YourElementName']");
    }


    @And("User MAXIMIZE_SCREEN")
    public static void userMaximizeScreen() throws Exception {

        driver.manage().window().maximize();
    }

    @And("User ENTER with {string} on {string}")
    public static void userEnterStringValueInLocator(String value, String textboxLocator) throws Exception {
        // Enter the provided value into the element with the locator ba_username

        String jsonString = readFileAsString("src/test/java/ObjectRepo/object.json");
        JSONObject jsonObject = new JSONObject(jsonString);

        if (textboxLocator.startsWith("<") && textboxLocator.endsWith(">") && value.startsWith("<") && value.endsWith(">")) {
            textboxLocator = textboxLocator.substring(1, textboxLocator.length() - 1);
            value = value.substring(1, value.length() - 1);
            if (jsonObject.has(textboxLocator)) {
                textboxLocator = jsonObject.getString(textboxLocator);
            } else {
                throw new JSONException("Variable not found in JSON: " + textboxLocator);
            }
            if (jsonObject.has(value)) {
                value = jsonObject.getString(value);
            } else {
                throw new JSONException("Variable not found in JSON: " + value);
            }
        }
        WebElement locator = driver.findElement(By.xpath(textboxLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", locator, value);
        //driver.findElement(By.xpath(textboxLocator)).sendKeys(phoneNumber);
    }


    @And("^User CAPTURE_SCREENSHOT$")
    public static void userCapturesScreenshot() {
        try {
            // Take screenshot
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Embed screenshot in Cucumber report
            message.attach(screenshot, "image/png", "screenshot");
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }


    @Given("User EXPLICIT_WAIT with {string} on {string}")
    public static void user_explicit_wait_with_on_locator(String seconds, String locator) throws Exception {
        // Write code here that turns the phrase above into concrete actions

        String jsonString = readFileAsString("src/test/java/ObjectRepo/object.json");
        JSONObject jsonObject = new JSONObject(jsonString);

        if (seconds.startsWith("<") && seconds.endsWith(">") && locator.startsWith("<") && locator.endsWith(">")) {
            seconds = seconds.substring(1, seconds.length() - 1);
            locator = locator.substring(1, locator.length() - 1);
            if (jsonObject.has(seconds)) {
                seconds = jsonObject.getString(seconds);
            } else {
                throw new JSONException("Variable not found in JSON: " + seconds);
            }
            if (jsonObject.has(locator)) {
                locator = jsonObject.getString(locator);
            } else {
                throw new JSONException("Variable not found in JSON: " + locator);
            }
        }

        try {
            // Convert waitInSec to int
            int waitInSeconds = Integer.parseInt(seconds);

            // Apply explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } catch (NumberFormatException e) {
            System.out.println("Invalid wait time format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error during explicit wait: " + e.getMessage());
        }
    }

    @Then("User VERIFY_TEXT_CONTAINS on {string} with {string}")
    public static void user_verify_text_contains_on_locator(String locator, String expectedText) throws Exception {
        // Write code here that turns the phrase above into concrete actions

        String jsonString = readFileAsString("src/test/java/ObjectRepo/object.json");
        JSONObject jsonObject = new JSONObject(jsonString);

        if (locator.startsWith("<") && locator.endsWith(">") && expectedText.startsWith("<") && expectedText.endsWith(">")) {
            locator = locator.substring(1, locator.length() - 1);
            expectedText = expectedText.substring(1, expectedText.length() - 1);

            if (jsonObject.has(locator)) {
                locator = jsonObject.getString(locator);
            } else {
                throw new JSONException("Variable not found in JSON: " + locator);
            }
            if (jsonObject.has(expectedText)) {
                expectedText = jsonObject.getString(expectedText);
            } else {
                throw new JSONException("Variable not found in JSON: " + expectedText);
            }
        }
        try {
            By elementLocator = By.xpath(locator); // Assuming locator is a CSS selector, you can change it based on your requirement
            WebElement element = driver.findElement(elementLocator);
            String actualText = element.getText();
            assertTrue("Expected text '" + expectedText + "' is not contained within actual text '" + actualText + "'",
                    actualText.contains(expectedText));
        } catch (Exception e) {
            System.out.println("Error verifying text: " + e.getMessage());
        }
    }


    private static String readFileAsString(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }


}
