package ios;

import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BrowserStackiOS {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
   	DesiredCapabilities capabilities = new DesiredCapabilities();
    	
    	// Set your access credentials
    	capabilities.setCapability("browserstack.user", "neerajkumar42");
    	capabilities.setCapability("browserstack.key", "MXmmyxNzZTYmXyyA8xyB");
    	
    	// Set URL of the application under test
    	capabilities.setCapability("app", "bs://a2c4c0bd114ce9765af0988a632982cb42688b81");
    	
    	// Specify device and os_version for testing
    	capabilities.setCapability("device", "iPhone 11 Pro");
    	capabilities.setCapability("os_version", "13");
        
    	// Set other BrowserStack capabilities
    	capabilities.setCapability("project", "First Java Project");
    	capabilities.setCapability("build", "Java iOS");
    	capabilities.setCapability("name", "first_test");
    	
    	
    	// Initialise the remote Webdriver using BrowserStack remote URL
    	// and desired capabilities defined above
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
        		new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
        

        // Test case for the BrowserStack sample iOS app. 
        // If you have uploaded your app, update the test case here. 
        IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();
        IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com");
        Thread.sleep(5000);
        IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));
        if(textOutput != null && textOutput.getText().equals("hello@browserstack.com"))
            assert(true);
        else
            assert(false);  
    
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();

	}

}
