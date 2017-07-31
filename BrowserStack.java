import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStack {
	
    public static String accessKey = "BROWSERSTACK_USERNAME";
    public static String userName = "BROWSERSTACK_ACCESS_KEY";

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("realMobile", true);
        capabilities.setCapability("device", "Samsung Galaxy S7");
        capabilities.setCapability("app", "bs://d561ad02f69d8a329e50e92471d170ed9916b671");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);

        WebElement searchElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.id("Search Wikipedia")));
        searchElement.click();
        WebElement insertTextElement = new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(By.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);

        List<WebElement> allProductsName = driver.findElements(By.className("android.widget.TextView"));
        assert(allProductsName.size() > 0);

        driver.quit();
    }
}
