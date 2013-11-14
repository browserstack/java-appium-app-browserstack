package bs;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        WebDriver driver = new RemoteWebDriver(
            new URL("http://USERNAME:ACCESS_KEY@hub.browserstack.com/wd/hub"),
            capability
            );
        driver.get("http://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        System.out.println("And the Title is: " + driver.getTitle());
        driver.quit();
    }
}
