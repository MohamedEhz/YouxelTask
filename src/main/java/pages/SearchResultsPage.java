package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class SearchResultsPage extends PageBase{
    // Constructor that accepts WebDriver and initializes PageFactory
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize WebElements with PageFactory
    }
    @FindBy(xpath = "//div[contains(@class, 'gKIa-Dp')]\n")
    WebElement searchResultName;
    @FindBy(xpath = "//div[contains(@class, 'dRkNeo') and contains(@class, 'grid')]")
    List<WebElement> products;

    public String searchResultInputName(){
       String text = wait.until(ExpectedConditions.visibilityOf(searchResultName)).getText().trim();
        System.out.println("Text before trimming: '" + text + "'");
        return text.replace("\"", "");
    }
    public int getNumberOfProducts(){
        return products.size();
    }
    // Click on the first product if available
    public void clickFirstProduct() {
        if (!products.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(products.get(0))).click(); // Click on the first product
        } else {
            throw new NoSuchElementException("No products found to click.");
        }
    }
}
