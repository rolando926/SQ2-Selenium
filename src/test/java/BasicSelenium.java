import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/8/2017.
 */
public class BasicSelenium {
    // Instantiation of our Utilities class
    static Overlay utils = new Overlay();
    String item = "hammer";

    // All my DOM objects

    public static final String HOME_PAGE = "http://homedepot.com";

    @Before
    public void setup(){
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
                utils.clickButton(utils.SEARCH_BUTTON));
        System.out.println("Validated search button is present and event = click.");

        // Verify we land on the correct page = Landing Page
        Assert.assertTrue(utils.verifyLandingPage(utils.LANDING_PAGE));
        System.out.println("Validated landing contains word "+item);

    }

    public void validateItem(){

        // Verify user can select 3rd item from list
        Assert.assertTrue("Could not validate 3rd Add To Cart item is clickable.",
                utils.verifyAnyAddToCartButtonCanBeSelected(utils.LANDING_PAGE,utils.ADD_TO_CART_ITEM,3));
        System.out.println("Validated 3rd item added to cart.");

        utils.driver.switchTo().frame(utils.driver.findElement(By.xpath("(.//iframe[@class='thd-overlay-frame'])[2]")));
        if(utils.driver.findElement(By.xpath(".//span[@class='u__husky']")).isDisplayed()){
            System.out.println("Validated Overlay information.");
        }
        utils.driver.switchTo().defaultContent();


        // Verify we land at the Overlay after selecting Add To Cart
        //Assert.assertTrue("Could not verify Overlay division.",
        //        utils.verifyOverlayPage());

        //utils.syncElement("SECONDS",10);
    }

    @Test
    public void getDescriptionHammer(){
        utils.validateItemDescriptionFromPrice();
    }

    @AfterClass
    public static void cleanUp(){
        // Close the driver
        //utils.driver.close();
    }
}
