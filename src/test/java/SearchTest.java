import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status; // Import the Status class
import pages.*;

public class SearchTest extends BaseTest {
    @Test(priority = 1)
    public void searchWithEmpty(){
        test.log(Status.INFO, "Starting the Search Test"); // Log the start of the test
        HomePage homePage = new HomePage(driver);
        // Perform search
        test.log(Status.INFO, "Entering empty search: " + properties.getProperty("emptySearch")); // Log search action
        homePage.enterItemInSearchField(properties.getProperty("emptySearch"));
        // Get the actual URL
        String actualURL = driver.getCurrentUrl();
        // Assert that URL isn't changed
        test.log(Status.INFO, "Asserting that the url isn't change."); // Log assertion action
        Assert.assertEquals(actualURL, homePage.currentOfHomeURL());

    }
    @Test(priority = 2)
    public void searchByKeyWord(){
        test.log(Status.INFO, "Starting the Search Test"); // Log the start of the test

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductDetailsPage productPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Perform search
        test.log(Status.INFO, "Entering item in search field: " + properties.getProperty("searchItems")); // Log search action
        homePage.enterItemInSearchField(properties.getProperty("searchItems"));

        // Get the actual text of the search result
        String actualText = searchResultsPage.searchResultInputName();

        // Assert that the search result matches the expected text
        test.log(Status.INFO, "Asserting that the search result matches the expected text."); // Log assertion action
        Assert.assertEquals(actualText, properties.getProperty("searchItems"));
    }
    @Test(priority = 3)
    public void searchByCategory(){
        test.log(Status.INFO, "Starting the Search Test"); // Log the start of the test
        HomePage homePage = new HomePage(driver);
        CategorySearchPage categorySearchPage = new CategorySearchPage(driver);
        test.log(Status.INFO, "Perform search by category.");
        //Perform search by category
        homePage.clickFirstCategory();
        //Check search by category
        test.log(Status.INFO, "Asserting that the search by category."); // Log assertion action
        Assert.assertNotEquals(homePage.currentOfHomeURL(),categorySearchPage.currentCategoryResultURL());
    }
}
