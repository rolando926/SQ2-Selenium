import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/8/2017.
 */
public class BasicSelenium {
    // Instantiation of our Utilities class
    LandingPage utils = new LandingPage();

    // All my DOM objects

    public static final String HOME_PAGE = "http://homedepot.com";

    @Test
    public void validateItem(){
        String item = "hammer";

        // Open URL page based on the driver object
        Assert.assertTrue("Could not validate main page.",
                utils.navigateURL(HOME_PAGE));
        System.out.println("Validated successful navigation to "+HOME_PAGE);

        //Search for header search bar and enter hammer
        Assert.assertTrue("Could not validate search box.",
                utils.enterItemIntoSearchBox(utils.SEARCH_BOX,item));
        System.out.println("Validated search box is present and entered "+item);

        // Click on search button
        Assert.assertTrue("Could not validate search button.",
                utils.validateButton(utils.SEARCH_BUTTON));
        System.out.println("Validated search button is present and event = click.");

        // Verify we land on the correct page = Landing Page
        Assert.assertTrue(utils.verifyLandingPage(utils.LANDING_PAGE));
        System.out.println("Validated landing contains word "+item);

        // Verify user can select 3rd item from list
        Assert.assertTrue(utils.verifyAnyAddToCartButtonCanBeSelected(utils.LANDING_PAGE,utils.ADD_TO_CART_ITEM,3));

        utils.syncElement("SECONDS",10);

        // Close the driver
        utils.driver.close();
    }
}
