package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor to initialize WebDriver and PageFactory
    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust timeout as needed
    }
}
