import com.browserstack.local.Local;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;


public class LocalSample {
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
        capabilities.setCapability("device", "Samsung Galaxy S7");
        capabilities.setCapability("app", "bs://d561ad02f69d8a329e50e92471d170ed9916b671");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);

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
          throw new Error("Cannot find the needed TextView element from app");
        }
        String matchedString = testElement.getText();
        System.out.println(matchedString);
        assert(matchedString.contains("The active connection is wifi"));
        assert(matchedString.contains("Up and running"));

        driver.quit();

        tearDownLocal();
    }
}
