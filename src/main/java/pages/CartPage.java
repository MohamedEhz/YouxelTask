package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends PageBase{
    // Constructor that accepts WebDriver and initializes PageFactory
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this); // Initialize WebElements with PageFactory
    }
    @FindBy(xpath = "//strong[text()='عربة التسوق']")
    WebElement cartPage;
    public boolean cartNameTitle(){
        return cartPage.isDisplayed();
    }
}
