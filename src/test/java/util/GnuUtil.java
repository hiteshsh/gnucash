package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

/**
 * Created by hiteshs on 12/6/18.
 */
public class GnuUtil {

    private AppiumDriver driver;

    public GnuUtil(AppiumDriver driver){
        this.driver=driver;
    }

    public void scrollElement(MobileElement element){
        TouchAction action = new TouchAction(driver);
        Dimension d= element.getSize();
        action.press(ElementOption.element(element,d.getWidth()/2,d.getHeight()-20))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(element,d.getWidth()/2,d.getHeight()/2+150)).release().perform();
    }

    public static void waitFor(long milisec){
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleAlertIfPresent(){
        try {
            WebDriverWait alertWait= new WebDriverWait(driver,10);
            alertWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();
        }catch (Exception e){
            System.out.println("No alert found");
        }
    }
}
