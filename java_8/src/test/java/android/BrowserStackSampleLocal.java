package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

public class BrowserStackSampleLocal {

  public static void main(String[] args) throws Exception {

    DesiredCapabilities capabilities = new DesiredCapabilities();
   
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

  }
}
