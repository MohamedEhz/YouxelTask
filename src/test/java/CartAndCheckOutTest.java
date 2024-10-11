import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultsPage;

public class CartAndCheckOutTest extends BaseTest{
    @Test
    public void shoppingCartAndCheckoutProcess() {
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

        // Assert the search result > 0
        test.log(Status.INFO, "Asserting that search results are found."); // Log assertion action
        Assert.assertTrue(searchResultsPage.getNumberOfProducts() > 0, "Search results found.");

        // Click on the first item
        test.log(Status.INFO, "Clicking on the first product."); // Log clicking action
        searchResultsPage.clickFirstProduct();

        // Add product to cart
        test.log(Status.INFO, "Adding product to cart."); // Log adding action
        productPage.addTocart();

        // Assert that the product is successfully added to the cart
        test.log(Status.INFO, "Asserting that the product is added successfully to the cart list."); // Log assertion action
        Assert.assertTrue(productPage.cartList(), "Product is added successfully to cart list");

        // Click on CheckOut button
        test.log(Status.INFO, "Clicking on Checkout button."); // Log clicking action
        productPage.clickOnCheckOutBtn();

        // Assert cart page is opened
        test.log(Status.INFO, "Asserting that the cart page is opened."); // Log assertion action
        Assert.assertTrue(cartPage.cartNameTitle(), "Cart page is opened");

        test.log(Status.PASS, "Search Test completed successfully."); // Log successful completion
    }
}
