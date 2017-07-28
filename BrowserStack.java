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

public class BrowserStack {

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("realMobile", true);
        capabilities.setCapability("device", "Samsung Galaxy S6");
        capabilities.setCapability("app", "bs://3fc0a1f5a158e935ad806b97288f4b24e11ebcc4");

        AndroidDriver driver = new AndroidDriver(new URL("http://BROWSERSTACK_USERNAME:BROWSERSTACK_ACCESS_KEY@hub.browserstack.com/wd/hub"), capabilities);

        WebElement searchElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.id("Search Wikipedia")));
        searchElement.click();
        WebElement insertTextElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);

        List<WebElement> allProductsName = driver.findElements(By.className("android.widget.TextView"));
        assertTrue(allProductsName.size() > 0);

        driver.quit();
    }
}
