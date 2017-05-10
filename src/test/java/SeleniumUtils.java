import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by RXC8414 on 5/9/2017.
 */
public class SeleniumUtils {
    public static final int MAX_TIME = 10;
    public WebDriver driver;

    SeleniumUtils(){
        //Setting Chrome driver properties
        System.setProperty("webdriver.chrome.driver","C:\\GitHub Repos\\DotCom\\chromedriver.exe");
        // Completing instantiation
        driver = new ChromeDriver();
    }

    public boolean waitUntilElementDisplayed(String element){
        int counter = 0;
        do{
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){
                return false;
            }
            if(driver.findElement(By.xpath(element)).isDisplayed()){
                return true;
            }

            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            counter++;
        }while(counter < MAX_TIME);
        return false;
    }

    public boolean navigateURL(String url){
        try{
            driver.get(url);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean verifyLandingPage(String value, String xpath){
        if(driver.findElement(By.xpath(xpath)).isDisplayed()){
            return true;
        }
        return false;
    }

    public boolean validateTextBox(String element, String text){
        if(waitUntilElementDisplayed(element)){
            try {
                driver.findElement(By.xpath(element)).sendKeys(text);
            }catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean validateButton(String element){
        if(waitUntilElementDisplayed(element)){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                driver.findElement(By.xpath(element)).click();
            }catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }
}
