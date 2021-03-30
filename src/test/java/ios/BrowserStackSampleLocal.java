package ios;

import com.browserstack.local.Local;
import java.net.URL; import java.io.File; import java.util.*;
import org.apache.commons.io.FileUtils;
import io.appium.java_client.MobileBy; import io.appium.java_client.ios.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;import org.openqa.selenium.remote.*;

public class BrowserStackSampleLocal {
	
    private static Local localInstance;
    public static String userName = "YOUR_USERNAME";
    public static String accessKey = "YOUR_ACCESS_KEY";
  

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
        // Start the BrowserStack Local binary
        setupLocal();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set your access credentials
    	  capabilities.setCapability("browserstack.user", userName);
        capabilities.setCapability("browserstack.key", accessKey);
        
        // Set URL of the application under test
       capabilities.setCapability("app", "bs://<app-id>");
       
       // Specify device and os_version for testing
       capabilities.setCapability("device", "iPhone 11 Pro");
       capabilities.setCapability("os_version", "13");

      // Set the browserstack.local capability to true
      capabilities.setCapability("browserstack.local", true);

      // Set other BrowserStack capabilities
    	capabilities.setCapability("project", "First Java Project");
    	capabilities.setCapability("build", "Java iOS Local");
    	capabilities.setCapability("name", "local_test");
        
   	  // Initialise the remote Webdriver using BrowserStack remote URL
      // and desired capabilities defined above
      IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
        new URL("http://hub.browserstack.com/wd/hub"), capabilities);

        // Test case for the BrowserStack sample iOS Local app. 
        // If you have uploaded your app, update the test case here.   
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

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
        
        // Stop the BrowserStack Local binary
        tearDownLocal();
		
	}

}
