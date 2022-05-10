package android;

import com.browserstack.local.Local;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.time.Duration;
import java.util.*;
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
    capabilities.setCapability("deviceName", "Google Pixel 3");
    capabilities.setCapability("platformName", "android");
    capabilities.setCapability("platformVersion", "9.0");

    // Initialise the remote Webdriver using BrowserStack remote URL
    // and desired capabilities defined above
    AndroidDriver driver = new AndroidDriver(
      new URL("http://hub.browserstack.com/wd/hub"),
      capabilities
    );

    // Test case for the BrowserStack sample Android Local app.
    // If you have uploaded your app, update the test case here.
    WebElement searchElement = new WebDriverWait(driver, Duration.ofSeconds(30))
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.id("com.example.android.basicnetworking:id/test_action")
        )
      );
    searchElement.click();

    WebElement insertTextElement = (WebElement) new WebDriverWait(
      driver,
      Duration.ofSeconds(30)
    )
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.className("android.widget.TextView")
        )
      );

    WebElement testElement = null;
    List<WebElement> allTextViewElements = driver.findElements(
      AppiumBy.className("android.widget.TextView")
    );
    Thread.sleep(10);
    for (WebElement textElement : allTextViewElements) {
      if (textElement.getText().contains("The active connection is")) {
        testElement = textElement;
      }
    }

    if (testElement == null) {
      throw new Error("Cannot find the needed TextView element from app");
    }
    String matchedString = testElement.getText();
    System.out.println(matchedString);
    assert (matchedString.contains("The active connection is wifi"));
    assert (matchedString.contains("Up and running"));

    // Invoke driver.quit() after the test is done to indicate that the test is completed.
    driver.quit();

    // Stop the BrowserStack Local binary
    tearDownLocal();
  }
}
