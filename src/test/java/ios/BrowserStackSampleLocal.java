package ios;

import com.browserstack.local.Local;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

public class BrowserStackSampleLocal {

  private static Local localInstance;
  public static String userName = "YOUR_USERNAME";
  public static String accessKey = "YOUR_ACCESS_KEY";

  public static void setupLocal() throws Exception {
    localInstance = new Local();
    Map<String, String> options = new HashMap<String, String>();
    options.put("key", accessKey);
    options.put("local", "true");
    localInstance.start(options);
  }

  public static void tearDownLocal() throws Exception {
    localInstance.stop();
  }

  public static void main(String[] args) throws Exception {
    // Start the BrowserStack Local binary
    setupLocal();

    DesiredCapabilities capabilities = new DesiredCapabilities();
    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

    // Set your access credentials
    browserstackOptions.put("userName", userName);
    browserstackOptions.put("accessKey", accessKey);

    // Set other BrowserStack capabilities
    browserstackOptions.put("appiumVersion", "1.22.0");
    browserstackOptions.put("projectName", "First Java Project");
    browserstackOptions.put("buildName", "browserstack-build-1");
    browserstackOptions.put("sessionName", "local_test");

    // Set the browserstack.local capability to true
    browserstackOptions.put("local", "true");

    // Passing browserstack capabilities inside bstack:options
    capabilities.setCapability("bstack:options", browserstackOptions);

    // Set URL of the application under test
    capabilities.setCapability("app", "bs://<app-id>");

    // Specify device and os_version for testing
    capabilities.setCapability("deviceName", "iPhone 11 Pro");
    capabilities.setCapability("platformName", "ios");
    capabilities.setCapability("platformVersion", "13");

    // Initialise the remote Webdriver using BrowserStack remote URL
    // and desired capabilities defined above
    IOSDriver driver = new IOSDriver(
      new URL("http://hub.browserstack.com/wd/hub"),
      capabilities
    );

    // Test case for the BrowserStack sample iOS Local app.
    // If you have uploaded your app, update the test case here.
    WebElement testButton = (WebElement) new WebDriverWait(
      driver,
      Duration.ofSeconds(30)
    )
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.accessibilityId("TestBrowserStackLocal")
        )
      );
    testButton.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(
      new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver d) {
          String result = d
            .findElement(AppiumBy.accessibilityId("ResultBrowserStackLocal"))
            .getAttribute("value");
          return result != null && result.length() > 0;
        }
      }
    );
    WebElement resultElement = (WebElement) driver.findElement(
      AppiumBy.accessibilityId("ResultBrowserStackLocal")
    );

    String resultString = resultElement.getText().toLowerCase();
    System.out.println(resultString);
    if (resultString.contains("not working")) {
      File scrFile = (File) ((TakesScreenshot) driver).getScreenshotAs(
          OutputType.FILE
        );
      FileUtils.copyFile(
        scrFile,
        new File(System.getProperty("user.dir") + "/screenshot.png")
      );
      System.out.println(
        "Screenshot stored at " +
        System.getProperty("user.dir") +
        "/screenshot.png"
      );
      throw new Error("Unexpected BrowserStackLocal test result");
    }

    String expectedString = "Up and running";
    assert (resultString.contains(expectedString.toLowerCase()));

    // Invoke driver.quit() after the test is done to indicate that the test is completed.
    driver.quit();

    // Stop the BrowserStack Local binary
    tearDownLocal();
  }
}
