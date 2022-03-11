package ios;

import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackSample {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
        
        DesiredCapabilities caps = new DesiredCapabilities();
            
        // Set your access credentials
        caps.setCapability("browserstack.user", "YOUR_USERNAME");
		caps.setCapability("browserstack.key", "YOUR_ACCESS_KEY");
    	
    	// Set URL of the application under test
    	caps.setCapability("app", "bs://<app-id>");
        
        // Specify device and os_version for testing
        caps.setCapability("deviceName", "iPhone 11 Pro");
        caps.setCapability("platformName", "ios");
    	caps.setCapability("platformVersion", "13");
        
        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");
        
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        RemoteWebDriver driver = new RemoteWebDriver(
                new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);
        
        // Test case for the BrowserStack sample iOS app. 
        // If you have uploaded your app, update the test case here. 
        WebElement textButton = (WebElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
        textButton.click();

        WebElement textInput = (WebElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com");
        Thread.sleep(5000);

        WebElement textOutput = (WebElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));
        if(textOutput != null && textOutput.getText().equals("hello@browserstack.com"))
            assert(true);
        else
            assert(false);  

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
}
