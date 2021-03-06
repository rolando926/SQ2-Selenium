import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by RXC8414 on 5/11/2017.
 */
public class LandingPage extends MainPage {
    public static final String LANDING_PAGE = ".//span[@class='original-keyword " +
            "u__regular' and contains(text(),'TO_REPLACE')]";
    public static final String ADD_TO_CART_ITEM = "(.//span[@class='bttn__content' " +
            "and text() = 'Add To Cart'])[]";
    public static final String WRAPPER = ".//div[contains(@class,'plp-pod plp-pod--default pod-item--')]";
    public static final String PRICE = ".//div[@class='price']";
    public static final String DESCRIPTION = ".//div[@class='pod-plp__description js-podclick-analytics']//a";

    ///////// Using the By class /////////
    public static final By DESC = By.xpath(".//div[@class='pod-plp__description js-podclick-analytics']//a");

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

    public boolean verifyLandingPage(String item){
        String str = LANDING_PAGE;
        str = str.replace("TO_REPLACE",item);
        if(super.verifyLandingPage(str)){
            return true;
        }
        return false;
    }


    public boolean validateItemDescriptionFromPrice(){
        if(waitUntilElementDisplayed(WRAPPER)){
            //List<WebElement> list = driver.findElements(By.xpath(WRAPPER));
            for (WebElement element:getElements(WRAPPER)) {
                String price = element.findElement(By.xpath(PRICE)).getText();
                price = price.substring(1,price.length()-2);
                int thePrice = Integer.parseInt(price);
                if(thePrice >= 10 && thePrice < 15){
                    String desc = element.findElement(By.xpath(DESCRIPTION)).getText();
                    System.out.println(desc);
                    System.out.println(price);
                    return true;
                }

            }
        }
        return false;
    }

    public boolean validateCanSelectNItem(String item, int n){
        int counter = 1;
        if(waitUntilElementDisplayed(WRAPPER)){
            for(WebElement element:getElements(WRAPPER)){
                String desc = element.findElement(DESC).getText();
                if(desc.contains(item)){
                    if(counter == n){
                        System.out.println("Description = "+desc);
                        String price = element.findElement(By.xpath(PRICE)).getText();
                        System.out.println("Price = "+Integer.parseInt(price.substring(1,price.length()))/100.0);
                        return true;
                    }
                    counter++;
                }
            }
        }
        return false;
    }
}
