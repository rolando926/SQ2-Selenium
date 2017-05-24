import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by RXC8414 on 5/9/2017.
 */
public class SeleniumUtils {
    public static final int MAX_TIME = 15;
    public WebDriver driver;

    public SeleniumUtils(){
        //Setting Chrome driver properties
        String path = System.getProperty("user.dir")+"\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        // Completing instantiation
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Waits for an element to display a property (display, enable, location)
    public boolean waitUntilElementDisplayed(String expression){
        int counter = 0;
        do {
            if (verifyDisplayed(expression)){
                return true;
            }
            else if (counter > 3){
                if(verifyEnabled(expression)){
                    return true;
                }

                else if (verifyLocation(expression)){
                    return true;
                }
            }

            counter++;
            try {
                TimeUnit.MILLISECONDS.sleep(950);
            }catch(Exception e){
                return false;
            }
        } while (counter < 20);

        return false;
    }

    public boolean waitUntilElementDisplayed(By expression){
        int counter = 0;
        do {
            if (verifyDisplayed(expression)){
                return true;
            }
            else if (counter > 3){
                if(verifyEnabled(expression)){
                    return true;
                }

                else if (verifyLocation(expression)){
                    return true;
                }
            }

            counter++;
            try {
                TimeUnit.MILLISECONDS.sleep(950);
            }catch(Exception e){
                return false;
            }
        } while (counter < 20);

        return false;
    }

    public boolean verifyDisplayed(String expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isDisplayed()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean verifyDisplayed(By expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(expression).isDisplayed()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean verifyEnabled(String expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isEnabled()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean verifyEnabled(By expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(expression).isEnabled()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean verifyLocation(String expression){
        try{
            TimeUnit.MILLISECONDS.sleep(50);
            if(driver.findElement(By.xpath(expression)).getLocation().x < 0 ||
                    driver.findElement(By.xpath(expression)).getLocation().y < 0){
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean verifyLocation(By expression){
        try{
            TimeUnit.MILLISECONDS.sleep(50);
            if(driver.findElement(expression).getLocation().x < 0 ||
                    driver.findElement(expression).getLocation().y < 0){
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
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
        try{
            if(waitUntilElementDisplayed(xpath)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean verifyLandingPage(By xpath){
        // Make sure element is displayed on landing page
        // Validation happening through XPath
        try{
            if(waitUntilElementDisplayed(xpath)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
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

    public boolean validateTextBox(By element){
        if(waitUntilElementDisplayed(element)){
            return true;
        }
        return false;
    }

    // Entering text into a text box
    public boolean enterTextIntoTextBox(String element, String strText){

        try {
            if (waitUntilElementDisplayed(element)) {
                //driver.findElement(By.xpath(element)).clear();
                try {
                    driver.findElement(By.xpath(element)).sendKeys(strText);
                }catch(Exception e){
                    System.out.println("Here is the exception.");
                }
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean enterTextIntoTextBox( By element, String strText){

        try {
            if (waitUntilElementDisplayed(element)) {
                //driver.findElement(By.xpath(element)).clear();
                try {
                    driver.findElement(element).sendKeys(strText);
                }catch(Exception e){
                    System.out.println("Here is the exception.");
                }
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean clickButton(String element){
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

    public boolean clickButton(By element){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); //Syncing
                driver.findElement(element).click(); // command for click
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

    public String insertIndexIntoXpath(String xpath, int index){
        return xpath.replace("[]","["+ index +"]");
    }

    public boolean switchDriver(String target, String iFrame){
        if(target.toUpperCase().equals("IFRAME")){
            try {
                driver.switchTo().frame(driver.findElement(By.xpath(iFrame)));
                return true;
            }catch(Exception e){
                return false;
            }
        }
        else{
            try {
                driver.switchTo().defaultContent();
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }

    public boolean switchDriver(String target, By iFrame){
        if(target.toUpperCase().equals("IFRAME")){
            try {
                driver.switchTo().frame(driver.findElement(iFrame));
                return true;
            }catch(Exception e){
                return false;
            }
        }
        else{
            try {
                driver.switchTo().defaultContent();
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }

    public List<WebElement> getElements(String xpath){
        try{
            List<WebElement> list = driver.findElements(By.xpath(xpath));
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<WebElement> getElements(By xpath){
        try{
            List<WebElement> list = driver.findElements(xpath);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
