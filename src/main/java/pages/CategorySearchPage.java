package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CategorySearchPage extends PageBase{
    public CategorySearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@data-qa='الفئة']")
    WebElement sideList;
    public String currentCategoryResultURL(){
        wait.until(ExpectedConditions.visibilityOf(sideList));
        return driver.getCurrentUrl();
    }
}
