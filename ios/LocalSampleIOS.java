import com.browserstack.local.Local;

import java.net.URL;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LocalSampleIOS {
    private static Local localInstance;
    public static String accessKey = "BROWSERSTACK_USERNAME";
    public static String userName = "BROWSERSTACK_ACCESS_KEY";
  

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
        capabilities.setCapability("device", "iPhone 7");
        capabilities.setCapability("app", "bs://<hashed app-id>");
        capabilities.setCapability("automationName", "XCUITest");

        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://"+userName+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);

        IOSElement testButton = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("TestBrowserStackLocal")));
        testButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver d) {
            String result = d.findElement(MobileBy.AccessibilityId("ResultBrowserStackLocal")).getAttribute("value");
            return result != null && result.length() > 0;
          }
        });
        IOSElement resultElement = (IOSElement) driver.findElement(MobileBy.AccessibilityId("ResultBrowserStackLocal"));

        String resultString = resultElement.getText().toLowerCase();
        System.out.println(resultString);
        if(resultString.contains("not working")) {
          File scrFile = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshot.png"));
          System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "/screenshot.png");
          throw new Error("Unexpected BrowserStackLocal test result");
        }

        String expectedString = "Up and running";
        assert(resultString.contains(expectedString.toLowerCase()));

        driver.quit();

        tearDownLocal();
    }
}
