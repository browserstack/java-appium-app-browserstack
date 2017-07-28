import com.browserstack.local.Local;

import java.net.URL;
import java.net.MalformedURLException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalSample {
    private static Local localInstance;

    public static void setupLocal() throws Exception {
      localInstance = new Local();
      Map<String, String> options = new HashMap<String, String>();
      options.put("key", accessKey);
      localInstance.start(options);
    }

    public static void tearDownLocal() throws Exception {
      localInstance.stop();
    }

    public static void main(String[] args) throws Exception {
        setupLocal();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserstack.local", true);
        capabilities.setCapability("realMobile", true);
        capabilities.setCapability("device", "Samsung Galaxy S6");
        capabilities.setCapability("app", "bs://3fc0a1f5a158e935ad806b97288f4b24e11ebcc4");

        AndroidDriver driver = new AndroidDriver(new URL("http://BROWSERSTACK_USERNAME:BROWSERSTACK_ACCESS_KEY@hub.browserstack.com/wd/hub"), capabilities);

        WebElement searchElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.id("com.example.android.basicnetworking:id/test_action")));
        searchElement.click();
        WebElement insertTextElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.className("android.widget.TextView")));

        WebElement testElement = null;
        List<WebElement> allTextViewElements = driver.findElements(By.className("android.widget.TextView"));
        Thread.sleep(10);
        for(WebElement textElement : allTextViewElements) {
          System.out.println(textElement.getText());
          if(textElement.getText().contains("The active connection is")) {
            testElement = textElement;
          }
        }

        if(testElement == null) {
          File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "screenshot.png"));
          System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "screenshot.png");
          throw new Error("Cannot find the needed TextView element from app");
        }
        String matchedString = testElement.getText();
        System.out.println(matchedString);
        assertTrue(matchedString.contains("The active connection is wifi"));
        assertTrue(matchedString.contains("Up and running"));

        driver.quit();

        tearDownLocal();
    }
}
