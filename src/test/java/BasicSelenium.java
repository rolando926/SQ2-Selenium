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

    @Test
    public void openBrowser(){
        // Open URL page based on the driver object
        utils.driver.get("http://www.homedepot.com");
        utils.waitElementDisplayed();

        //Search for header search bar and enter hammer
        utils.driver.findElement(By.xpath(SEARCH_BOX)).sendKeys("hammer");
        utils.waitElementDisplayed();

        // Click on search button
        utils.driver.findElement(By.xpath(SEARCH_BUTTON)).click();
        utils.waitElementDisplayed();

        Assert.assertTrue(utils.verifyLandingPage("hammer",LANDING_PAGE));

        // Close the driver
        utils.driver.close();
    }
}
