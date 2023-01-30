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
public class BrowserStackSample {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
   	DesiredCapabilities caps = new DesiredCapabilities();
    	
    	// Set your access credentials
    	caps.setCapability("browserstack.user", "abdullazizxs_M0W5GX");
    	caps.setCapability("browserstack.key", "zS2Tit6YTbuhoCGgbB21");
    	
    	// Set URL of the application under test
    	caps.setCapability("app", "bs://ff266c82636fc47ee3a615747bc9a7b63857a03d");
    	
    	// Specify device and os_version for testing
        caps.setCapability("device", "iPhone XS");
        caps.setCapability("os_version", "12");
        
    	// Set other BrowserStack capabilities
    	caps.setCapability("project", "First Java Project");
    	caps.setCapability("build", "browserstack-build-1");
    	caps.setCapability("name", "first_test");
    	
    	
    	// Initialise the remote Webdriver using BrowserStack remote URL
    	// and desired capabilities defined above
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
        		new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);
         
        // Write your test case statements here
        
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
	}
}
