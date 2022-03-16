package ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserStackSample {

  public static void main(String[] args)
    throws MalformedURLException, InterruptedException {
    DesiredCapabilities caps = new DesiredCapabilities();
    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

    // Set your access credentials
    browserstackOptions.put("userName", "YOUR_USERNAME");
    browserstackOptions.put("accessKey", "YOUR_ACCESS_KEY");

    // Set other BrowserStack capabilities
    browserstackOptions.put("appiumVersion", "1.22.0");
    browserstackOptions.put("projectName", "First Java Project");
    browserstackOptions.put("buildName", "browserstack-build-1");
    browserstackOptions.put("sessionName", "first_test");

    // Passing browserstack capabilities inside bstack:options
    caps.setCapability("bstack:options", browserstackOptions);

    // Set URL of the application under test
    caps.setCapability("app", "bs://<app-id>");

    // Specify device and os_version for testing
    caps.setCapability("deviceName", "iPhone 11 Pro");
    caps.setCapability("platformName", "ios");
    caps.setCapability("platformVersion", "13");

    // Initialise the remote Webdriver using BrowserStack remote URL
    // and desired capabilities defined above
    IOSDriver driver = new IOSDriver(
      new URL("http://hub.browserstack.com/wd/hub"),
      caps
    );

    // Test case for the BrowserStack sample iOS app.
    // If you have uploaded your app, update the test case here.
    WebElement textButton = (WebElement) new WebDriverWait(
      driver,
      Duration.ofSeconds(30)
    )
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.accessibilityId("Text Button")
        )
      );
    textButton.click();

    WebElement textInput = (WebElement) new WebDriverWait(
      driver,
      Duration.ofSeconds(30)
    )
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.accessibilityId("Text Input")
        )
      );
    textInput.sendKeys("hello@browserstack.com");
    Thread.sleep(5000);

    WebElement textOutput = (WebElement) new WebDriverWait(
      driver,
      Duration.ofSeconds(30)
    )
    .until(
        ExpectedConditions.elementToBeClickable(
          AppiumBy.accessibilityId("Text Output")
        )
      );
    if (
      textOutput != null &&
      textOutput.getText().equals("hello@browserstack.com")
    ) assert (true); else assert (false);

    // Invoke driver.quit() after the test is done to indicate that the test is completed.
    driver.quit();
  }
}
