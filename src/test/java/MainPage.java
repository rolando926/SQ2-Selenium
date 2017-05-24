import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/11/2017.
 */
public class MainPage extends SeleniumUtils{

    public static final String SEARCH_BOX = ".//input[@id='headerSearch']";
    public static final String SEARCH_BUTTON = ".//button[@id='headerSearchButton']";
    public static final String STORE_LOCATOR = ".//*[@class='MyStore__store']";

    public boolean syncStoreLocation(){
        int counter = 0;
        do{
            // Grabs text from element
            try {
                String text = driver.findElement(By.xpath(STORE_LOCATOR)).getText();
                if (!text.contains("Choose")) {
                    return true;
                }
                syncElement("MILLISECONDS", 100);
                counter++;
            }catch(Exception e){
                continue;
            }
        }while(counter < 100);
        return false;
    }

    public boolean enterItemIntoSearchBox(String element, String text){
        if(syncStoreLocation()) {
            if (validateTextBox(element)) {
                if (enterTextIntoTextBox(element, text)) {
                    return true;
                }
            }
        }
        return false;
    }


}
