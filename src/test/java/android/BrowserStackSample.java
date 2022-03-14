package android;

import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.net.MalformedURLException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserStackSample {
	    protected static AppiumDriver driver;
    protected static Properties prop;
    private static AppiumDriverLocalService server;
    InputStream inputstream;
    TestUtils util = new TestUtils();

	//public static void main(String[] args) throws MalformedURLException, InterruptedException {
	
	 public BaseTest() {

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	 @BeforeClass
         @Parameters({"platformName", "deviceName", "udid"})	
    	 public void initialSetup(String platformName, String deviceName, String udid) {
        try {

            util.log().info("Initial set up has been started ...");
            prop = new Properties();
            String propFileName = "config.properties";
            inputstream = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputstream);
            util.log().info(propFileName + "loaded sucessfully...");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            util.log().info("Mobile automation platform used : " + platformName);

            caps.setCapability("newCommandTime", 300);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
            caps.setCapability(MobileCapabilityType.UDID, udid);
            caps.setCapability("avd", "Anubhav_Pixel_1");
            caps.setCapability("avdLaunchTimeOut", 180000);
            caps.setCapability("newCommandTimeOUt", 300);
            String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                    File.separator + "resources" + File.separator + "app" + File.separator + "app-release.apk";
            caps.setCapability(MobileCapabilityType.APP, appUrl);
           // caps.setCapability("appPackage", prop.getProperty("androidAppPackage"));
          // caps.setCapability("appActivity", prop.getProperty("androidAppActivity"));

            URL url = new URL(prop.getProperty("appiumURL"));
            driver = new AppiumDriver(url, caps);

            System.out.println("session id :" + driver.getSessionId());
            if (driver.getSessionId() != null) {
                util.log().info("Appium driver has been initialized sucessfully");
                util.log().info("Appirum driver session id is " + driver.getSessionId());
            }


            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            util.log().info("implicitly time out wait set to 20 second");
        } catch (Exception e) {
            e.printStackTrace();
            util.log().fatal("Appium Driver not initialized");
        }
    }

        // Test case for the BrowserStack sample Android app. 
        // If you have uploaded your app, update the test case here. 
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(
            		MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
		AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
             ExpectedConditions.elementToBeClickable(
            		 MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);
        List<AndroidElement> allProductsName = driver.findElementsByClassName(
        		"android.widget.TextView");
        assert(allProductsName.size() > 0);
        
        
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
		
	}

}
