package features;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.GnuUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeScreen extends BaseFeatures {

    @AndroidFindBy(xpath = "//*[@resource-id='org.gnucash.android:id/account_recycler_view']/android.widget.FrameLayout")
    private List<MobileElement> listOfAccounts;

    @AndroidFindBy(id="org.gnucash.android:id/fab_create_account")
    private MobileElement addAccountBtn;

    By primaryText=  MobileBy.id( "org.gnucash.android:id/primary_text");
    By secondaryText=  MobileBy.id( "org.gnucash.android:id/secondary_text");
    By balanceText=  MobileBy.id( "org.gnucash.android:id/account_balance");

    public HomeScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public HomeScreen dismissUpdatesAlert(){
        GnuUtil.waitFor(2000);
        util.handleAlertIfPresent();
        return this;
    }

    public HomeScreen verifyDefaultAccountDetails(){

        assertThat(listOfAccounts.get(0)
                .findElement(primaryText).getText()).isEqualTo("Assets");
        assertThat(listOfAccounts.get(0)
                .findElement(secondaryText).getText()).isEqualTo("1 sub-account");
        assertThat(listOfAccounts.get(0)
                .findElement(balanceText).getText()).isEqualTo("€0.00");
        assertThat(listOfAccounts.get(1)
                .findElement(primaryText).getText()).isEqualTo("Equity");
        assertThat(listOfAccounts.get(1)
                .findElement(secondaryText).getText()).isEqualTo("1 sub-account");
        assertThat(listOfAccounts.get(1)
                .findElement(balanceText).getText()).isEqualTo("€0.00");
        assertThat(listOfAccounts.get(2)
                .findElement(primaryText).getText()).isEqualTo("Expenses");
        assertThat(listOfAccounts.get(2)
                .findElement(secondaryText).getText()).isEqualTo("25 sub-accounts");
        assertThat(listOfAccounts.get(2)
                .findElement(balanceText).getText()).isEqualTo("€0.00");
        assertThat(listOfAccounts.get(3)
                .findElement(primaryText).getText()).isEqualTo("Income");
        assertThat(listOfAccounts.get(3)
                .findElement(secondaryText).getText()).isEqualTo("5 sub-accounts");
        assertThat(listOfAccounts.get(3)
                .findElement(balanceText).getText()).isEqualTo("€0.00");
        assertThat(listOfAccounts.get(4)
                .findElement(primaryText).getText()).isEqualTo("Liabilities");
        assertThat(listOfAccounts.get(4)
                .findElement(secondaryText).getText()).isEqualTo("1 sub-account");
        assertThat(listOfAccounts.get(4)
                .findElement(balanceText).getText()).isEqualTo("€0.00");

        return this;
    }

    public HomeScreen selectAccount(String account){
        GnuUtil.waitFor(1000);
        listOfAccounts.stream().
                filter(mobileEl->mobileEl.findElement(primaryText)
                        .getText().equalsIgnoreCase(account)).findFirst().get().click();
        return this;

    }


}
