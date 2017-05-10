import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by RXC8414 on 5/9/2017.
 */
public class SeleniumUtils {
    public static final int TIME_INTERVAL = 3;
    public WebDriver driver;

    SeleniumUtils(){
        //Setting Chrome driver properties
        System.setProperty("webdriver.chrome.driver","C:\\GitHub Repos\\DotCom\\chromedriver.exe");
        // Completing instantiation
        driver = new ChromeDriver();
    }

    public void waitElementDisplayed(){
        // Wait for 3 seconds
        try {
            TimeUnit.SECONDS.sleep(TIME_INTERVAL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyLandingPage(String value, String xpath){
        if(driver.findElement(By.xpath(xpath)).isDisplayed()){
            return true;
        }
        return false;
    }
}
