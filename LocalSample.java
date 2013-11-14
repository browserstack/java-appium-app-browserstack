import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;

public class LocalSample {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com/");
        System.out.println("Page title is: " + driver.getTitle());
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        System.out.println("And the Title is: " + driver.getTitle());
        driver.quit();

    }
}
