/**
 * Created by RXC8414 on 5/16/2017.
 */
public class Overlay extends LandingPage{
    public static final String OVERLAY = ".//*[contains(text(),'Added to Cart')]";
    public static final String FRAME_OVERLAY = "(.//iframe[@class='thd-overlay-frame'])[2]";

    public boolean verifyOverlayPage(){
        switchDriver("iframe",FRAME_OVERLAY);
        if(waitUntilElementDisplayed(OVERLAY)){

            switchDriver("","");
            return true;
        }
        return false;
    }
}
