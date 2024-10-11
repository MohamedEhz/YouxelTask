package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductDetailsPage extends PageBase{
    // Constructor that accepts WebDriver and initializes PageFactory
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize WebElements with PageFactory
    }
    @FindBy(xpath = "//div[@class=\"cart-button\"]")
    WebElement cartBtn;
    @FindBy(xpath = "//div[contains(@class,'fBbbhK')]")
    List<WebElement> cartItemsList;
    @FindBy(xpath = "//button[@id='checkout-btn' and contains(@class,'hIzwVz')] ")
    WebElement checkOutBtn;

    public void addTocart(){
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }
    public boolean cartList(){
       return !cartItemsList.isEmpty();
    }
    public void clickOnCheckOutBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
    }
}
