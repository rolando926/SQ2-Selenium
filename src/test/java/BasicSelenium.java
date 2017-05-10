import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/8/2017.
 */
public class BasicSelenium {
    // Instantiation of our Utilities class
    SeleniumUtils utils = new SeleniumUtils();

    // All my DOM objects
    public static final String SEARCH_BOX = ".//input[@id='headerSearch']";
    public static final String SEARCH_BUTTON = ".//button[@id='headerSearchButton']";
    public static final String LANDING_PAGE = ".//span[@class='original-keyword " +
            "u__regular' and contains(text(),'hammer')]";
    public static final String HOME_PAGE = "http://homedepot.com";

    @Test
    public void openBrowser(){
        String item = "hammer";

        // Open URL page based on the driver object
        Assert.assertTrue("Could not validate main page.",
                utils.navigateURL(HOME_PAGE));
        System.out.println("Validated successful navigation to "+HOME_PAGE);

        //Search for header search bar and enter hammer
        Assert.assertTrue("Could not validate search box.",
                utils.validateTextBox(SEARCH_BOX,item));
        System.out.println("Validated search box is present and entered "+item);

        // Click on search button
        Assert.assertTrue("Could not validate search button.",
                utils.validateButton(SEARCH_BUTTON));
        System.out.println("Validated search button is present and event = click.");

        Assert.assertTrue(utils.verifyLandingPage("hammer",LANDING_PAGE));
        System.out.println("Validated landing contains word "+item);

        // Close the driver
        utils.driver.close();
    }
}
