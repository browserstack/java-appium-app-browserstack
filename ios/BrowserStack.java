import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class BrowserStack {
    public static String accessKey = "BROWSERSTACK_USERNAME";
    public static String userName = "BROWSERSTACK_ACCESS_KEY";

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("realMobile", true);
        capabilities.setCapability("device", "iPhone 7");
        capabilities.setCapability("app", "bs://<hashed app-id>");
        capabilities.setCapability("automationName", "XCUITest");

        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://"+userName+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);

        IOSElement loginButton = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Log In")));
        loginButton.click();
        IOSElement emailTextField = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Email address")));

        // element.sendKeys() method is not supported in Appium 1.6.3
        // Workaround for sendKeys() method:
        emailTextField.click();
        String email = "hello@browserstack.com";
        for (int i = 0; i < email.length(); i++) {
          driver.findElementByXPath("//XCUIElementTypeKey[@name='" + email.charAt(i) + "']").click();
        }

        driver.findElementByAccessibilityId("Next").click();      
        Thread.sleep(5000);

        List<IOSElement> textElements = driver.findElementsByXPath("//XCUIElementTypeStaticText");
        assert(textElements.size() > 0);
        String matchedString = "";
        for(IOSElement textElement : textElements) {
          String textContent = textElement.getText();
          if(textContent.contains("not registered")) {
            matchedString = textContent;
          }
        }

        System.out.println(matchedString);
        assert(matchedString.contains("not registered on WordPress.com"));

        driver.quit();
    }
}
