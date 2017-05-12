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
    public static final String DRIVER_PATH = "C:\\GitHub Repos\\DotCom\\chromedriver.exe";

    SeleniumUtils(){
        //Setting Chrome driver properties
        System.setProperty("webdriver.chrome.driver",DRIVER_PATH);
        // Completing instantiation
        driver = new ChromeDriver();
    }

    public boolean waitUntilElementDisplayed(String element){
        // Start a zero and increment a second if element is not available up to a limit
        int counter = 0;
        do{
            // Verify if element is displayed or not
            if(driver.findElement(By.xpath(element)).isDisplayed()){
                return true; // if displayed return back
            }
            syncElement("SECONDS",1); // increment by 1 second
            counter++; // incrementing by a second
        }while(counter < MAX_TIME); // limit
        return false; // guilty until proven innocent
    }

    // Selenium command for navigating to a page
    public boolean navigateURL(String url){
        try{
            driver.get(url); // Selenium command
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // Verify that you land on the correct page base on an element verification
    public boolean verifyLandingPage(String xpath){
        // Make sure element is displayed on landing page
        // Validation happening through XPath
        if(driver.findElement(By.xpath(xpath)).isDisplayed()){
            return true;
        }
        return false;
    }

    // Validate presence of text box
    public boolean validateTextBox(String element){
        if(waitUntilElementDisplayed(element)){
            return true;
        }
        return false;
    }

    // Entering text into a text box
    public boolean enterTextIntoTextBox(String element, String text){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); // Syncing Selenium
                driver.findElement(By.xpath(element)).sendKeys(text); //command for entering text
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public boolean validateButton(String element){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); //Syncing
                driver.findElement(By.xpath(element)).click(); // command for click
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public void syncElement(String time, int amount){
        try {
            switch (time.toUpperCase()) {
                case "MILLI":
                case "MILLISECONDS":
                    TimeUnit.MILLISECONDS.sleep(amount);
                    break;
                case "SEC":
                case "SECONDS":
                    TimeUnit.SECONDS.sleep(amount);
                    break;
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
