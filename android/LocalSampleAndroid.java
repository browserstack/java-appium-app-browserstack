import com.browserstack.local.Local;

import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;


public class LocalSampleAndroid {
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
        capabilities.setCapability("device", "Samsung Galaxy S7");
        capabilities.setCapability("app", "bs://<hashed app-id>");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);

        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.id("com.example.android.basicnetworking:id/test_action")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.className("android.widget.TextView")));

        AndroidElement testElement = null;
        List<AndroidElement> allTextViewElements = driver.findElementsByClassName("android.widget.TextView");
        Thread.sleep(10);
        for(AndroidElement textElement : allTextViewElements) {
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
