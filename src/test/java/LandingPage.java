import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/11/2017.
 */
public class LandingPage extends MainPage {
    public static final String LANDING_PAGE = ".//span[@class='original-keyword " +
            "u__regular' and contains(text(),'hammer')]";
    public static final String ADD_TO_CART_ITEM = "(.//span[@class='bttn__content' " +
            "and text() = 'Add To Cart'])[]";

    public boolean verifyAnyAddToCartButtonCanBeSelected(String element1, String element2, int index){
        if(waitUntilElementDisplayed(element1)){
            element2 = insertIndexIntoXpath(element2, index);
            if(waitUntilElementDisplayed(element2)){
                driver.findElement(By.xpath(element2)).click();
                return true;
            }
        }
        return false;
    }
}
