package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends PageBase {

    // Constructor that accepts WebDriver and initializes PageFactory
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize WebElements with PageFactory
    }

    @FindBy(id = "searchBar")
    WebElement searchBarField;
    @FindBy(xpath = "//div[@class='componentArea-2']" +
            "//div[@class='visibilitySensorWrapper']" +
            "//div[contains(@data-qa,'widget_banner-module')]" +
            "/div[@class='swiper-wrapper' and contains(@style,'transform')]")
    List<WebElement> categoryList;

    public void enterItemInSearchField(String item) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBarField)).sendKeys(item);
        searchBarField.sendKeys(Keys.ENTER);  // Simulate pressing the Enter key
    }
    //Get the first category name
    public String FirstCategoryName() {
        if (!categoryList.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(categoryList.get(0))).getText(); // Click on the first product
        } else {
            throw new NoSuchElementException("No products found to click.");
        }
        return categoryList.get(0).getText();
    }
    // Click on the first product if available
    public void clickFirstCategory() {
        if (!categoryList.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(categoryList.get(0))).click(); // Click on the first product
        } else {
            throw new NoSuchElementException("No products found to click.");
        }
    }
    //Get current URL
    public String currentOfHomeURL(){
        wait.until(ExpectedConditions.visibilityOf(searchBarField));
        return driver.getCurrentUrl();
    }
}
