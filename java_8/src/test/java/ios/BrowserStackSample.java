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
    
    // Initialise the remote Webdriver using BrowserStack remote URL
    // sdk injects desired capabilities
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
