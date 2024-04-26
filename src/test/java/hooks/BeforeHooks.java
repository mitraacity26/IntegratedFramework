package hooks;

import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utility.BrowserDriver;
import utility.Common;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BeforeHooks extends BrowserDriver {


    @Before("@BeforeHook")
    public void setup() throws Exception {


        //Read json
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");

        JSONObject jsonObject = new JSONObject(jsonString);
        String ExpectedBrowser;
        String sauceLabMobileNativeIOS;
        String sauceLabMobileNativeAndroid;
        String sauceLabMobileWebIOS;
        String sauceLabMobileWebAndroid;
        String sauceLabWeb;
        String sauceURL;

        //Fetch expected browser value from json
        if (jsonObject.has("browser")) {
            ExpectedBrowser = jsonObject.getString("browser");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        //Fetch sauce lab value from json
        if (jsonObject.has("sauceLabWeb")) {
            sauceLabWeb = jsonObject.getString("sauceLabWeb");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("sauceLabMobileNativeIOS")) {
            sauceLabMobileNativeIOS = jsonObject.getString("sauceLabMobileNativeIOS");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("sauceLabMobileNativeAndroid")) {
            sauceLabMobileNativeAndroid = jsonObject.getString("sauceLabMobileNativeAndroid");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("sauceLabMobileWebIOS")) {
            sauceLabMobileWebIOS = jsonObject.getString("sauceLabMobileWebIOS");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("sauceLabMobileWebAndroid")) {
            sauceLabMobileWebAndroid = jsonObject.getString("sauceLabMobileWebAndroid");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("sauceURL")) {
            sauceURL = jsonObject.getString("sauceURL");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }

        //Launch local browser/sauce based on above value
        if (sauceLabWeb.compareToIgnoreCase("yes") == 0) {
            MutableCapabilities caps = getMutableCapabilities_Web();
            URL url2 = new URL(sauceURL);
            driver = new RemoteWebDriver(url2, caps);

        } else if (sauceLabMobileNativeAndroid.compareToIgnoreCase("yes") == 0) {
            MutableCapabilities caps = getMutableCapabilities_Android_Mobile();
            URL url2 = new URL(sauceURL);
            driver = new IOSDriver(url2, caps);
            ;
        } else if (sauceLabMobileNativeIOS.compareToIgnoreCase("yes") == 0) {
            MutableCapabilities caps = getMutableCapabilities_iOS_Mobile();
            URL url2 = new URL(sauceURL);
            driver = new IOSDriver(url2, caps);
        } else if (sauceLabMobileWebAndroid.compareToIgnoreCase("yes") == 0) {

            MutableCapabilities caps = getMutableCapabilities_Android_Web();
            URL url2 = new URL(sauceURL);
            driver = new IOSDriver(url2, caps);

        } else if (sauceLabMobileWebIOS.compareToIgnoreCase("yes") == 0) {
            MutableCapabilities caps = getMutableCapabilities_iOS_Web();
            URL url2 = new URL(sauceURL);
            driver = new IOSDriver(url2, caps);


        } else if (ExpectedBrowser.compareToIgnoreCase("Chrome") == 0) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-debugging-port=9222");
            driver = new ChromeDriver(options);
        } else if (ExpectedBrowser.compareToIgnoreCase("Edge") == 0) {
            driver = new EdgeDriver();
        } else if (ExpectedBrowser.compareToIgnoreCase("Firefox") == 0) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }


    }


    private static MutableCapabilities getMutableCapabilities_Web() throws Exception {
        //set capabilities for sauce-Web
        ChromeOptions caps = new ChromeOptions();
        caps.setPlatformName("Windows 11");
        caps.setBrowserVersion("latest");
        MutableCapabilities sauceOptions2 = userSpecific();
        caps.setCapability("sauce:options", sauceOptions2);
        return caps;
    }

    private static MutableCapabilities getMutableCapabilities_iOS_Mobile() throws Exception {
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");
        String app;
        String deviceName;
        JSONObject jsonObject = new JSONObject(jsonString);

        //Fetch expected browser value from json
        if (jsonObject.has("appium:app")) {
            app = jsonObject.getString("appium:app");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("appium:deviceName")) {
            deviceName = jsonObject.getString("appium:deviceName");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:app", app);  // The filename of the mobile app
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:automationName", "XCUITest");

        MutableCapabilities sauceOptions2 = userSpecific();
        caps.setCapability("sauce:options", sauceOptions2);
        return caps;
    }

    private static MutableCapabilities getMutableCapabilities_iOS_Web() throws Exception {
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");
        String browserName;
        String deviceName;
        JSONObject jsonObject = new JSONObject(jsonString);

        //Fetch expected browser value from json
        if (jsonObject.has("browser")) {
            browserName = jsonObject.getString("browser");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("appium:deviceName")) {
            deviceName = jsonObject.getString("appium:deviceName");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("browserName", browserName);

        MutableCapabilities sauceOptions2 = userSpecific();
        caps.setCapability("sauce:options", sauceOptions2);
        return caps;
    }


    private static MutableCapabilities getMutableCapabilities_Android_Mobile() throws Exception {
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");
        String app;
        String deviceName;
        JSONObject jsonObject = new JSONObject(jsonString);

        //Fetch expected browser value from json
        if (jsonObject.has("appium:app")) {
            app = jsonObject.getString("appium:app");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("appium:deviceName")) {
            deviceName = jsonObject.getString("appium:deviceName");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", app);  // The filename of the mobile app
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities sauceOptions2 = userSpecific();
        caps.setCapability("sauce:options", sauceOptions2);
        return caps;
    }

    private static MutableCapabilities getMutableCapabilities_Android_Web() throws Exception {
        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");
        String browserName;
        String deviceName;
        JSONObject jsonObject = new JSONObject(jsonString);

        //Fetch expected browser value from json
        if (jsonObject.has("browser")) {
            browserName = jsonObject.getString("browser");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("appium:deviceName")) {
            deviceName = jsonObject.getString("appium:deviceName");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("browserName", browserName);
        caps.setCapability("appium:deviceName", deviceName);
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities sauceOptions2 = userSpecific();
        caps.setCapability("sauce:options", sauceOptions2);
        return caps;
    }


    private static MutableCapabilities userSpecific() throws Exception {

        String jsonString = readFileAsString("src/test/java/ObjectRepo/central.json");

        JSONObject jsonObject = new JSONObject(jsonString);

        //Fetch expected browser value from json
        String username;
        String accessKey;
        String build;
        String name;

        if (jsonObject.has("username")) {
            username = jsonObject.getString("username");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("accessKey")) {
            accessKey = jsonObject.getString("accessKey");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("build")) {
            build = jsonObject.getString("build");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }
        if (jsonObject.has("name")) {
            name = jsonObject.getString("name");
        } else {
            throw new JSONException("Variable not found in JSON: ");
        }

        MutableCapabilities sauceOptions2 = new MutableCapabilities();
        sauceOptions2.setCapability("username", username);
        sauceOptions2.setCapability("accessKey", accessKey);
        sauceOptions2.setCapability("build", build);
        sauceOptions2.setCapability("name", name);
        return sauceOptions2;
    }

    @Before(order = 1)
    //This will supply scenario in userCapturesScreenshot method under common class
    public void setUpScenario(Scenario scenario) {
        Common.message = scenario;
    }

    //read File which is used in above driver initialization function
    private static String readFileAsString(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }


}
