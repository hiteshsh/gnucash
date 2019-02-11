package features;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GnuUtil;

/**
 * Created by hiteshs on 12/7/18.
 */
public class BaseFeatures {
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected GnuUtil util;

    public BaseFeatures(AppiumDriver driver){
        this.driver=driver;
        wait= new WebDriverWait(driver,10);
        util= new GnuUtil(driver);
    }
}
