package features;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountSetup extends BaseFeatures{

    @AndroidFindBy(id = "org.gnucash.android:id/btn_save")
    private MobileElement nextBtn;

    @AndroidFindBy(id="android:id/title")
    private MobileElement welcomeTxt;

    @AndroidFindBy(id = "android:id/list")
    private MobileElement checkboxList;

    public AccountSetup(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);

    }

    public HomeScreen setUpAccount(String currency,String accountType,String feedbackOtion){
        next().selectCurrency(currency)
        .next().selectAccountType(accountType)
        .next().selectFeedBackOption(feedbackOtion)
        .next().reviewAccountDetails(currency,accountType,feedbackOtion)
        .done();
        //GnuUtil.waitFor(4000);
        //util.handleAlertIfPresent();
        return new HomeScreen(driver);

    }
    public AccountSetup shouldShowWelcomeMessage(){
        assertThat(welcomeTxt.getText()).isEqualTo("Welcome to GnuCash");
        return this;
    }
    private AccountSetup next(){
        wait.until(ExpectedConditions.visibilityOf(nextBtn));
        nextBtn.click();
        return this;
    }
    private void done(){
        nextBtn.click();
    }

    private AccountSetup selectCurrency(String currency){
        checkboxList
                .findElementsByClassName("android.widget.CheckedTextView")
                .stream().filter(mobileElement -> mobileElement.getText().equalsIgnoreCase(currency))
                .findFirst().get().click();
        return this;

    }
    private AccountSetup selectAccountType(String accountType){
        checkboxList
                .findElementsByClassName("android.widget.CheckedTextView")
                .stream().filter(mobileElement -> mobileElement.getText().equalsIgnoreCase(accountType))
                .findFirst().get().click();
        return this;
    }
    private AccountSetup selectFeedBackOption(String feedbackOption){
        checkboxList
                .findElementsByClassName("android.widget.CheckedTextView")
                .stream().filter(mobileElement -> mobileElement.getText().equalsIgnoreCase(feedbackOption))
                .findFirst().get().click();
        return this;
    }

//    private void findElementByTextAndClassNameWithInElementList(MobileElement element,String text,String className){
//        element
//                .findElementsByClassName(className)
//                .stream().filter(mobileElement -> mobileElement.getText().equalsIgnoreCase(text))
//                .findFirst().get().click();
//    }

    private AccountSetup reviewAccountDetails(String currency,String accountType,String feedbackOtion){

        List<MobileElement> accountKey=checkboxList.findElementsById("android:id/text1");
        List<MobileElement> accountValue=checkboxList.findElementsById("android:id/text2");
        assertThat(accountKey.get(0).getText()).isEqualTo("DEFAULT CURRENCY");
        assertThat(accountValue.get(0).getText()).isEqualTo(currency);
        assertThat(accountKey.get(1).getText()).isEqualTo("ACCOUNT SETUP");
        assertThat(accountValue.get(1).getText()).isEqualTo(accountType);
        assertThat(accountKey.get(2).getText()).isEqualTo("FEEDBACK OPTIONS");
        assertThat(accountValue.get(2).getText()).isEqualTo(feedbackOtion);

        return this;
    }
}
