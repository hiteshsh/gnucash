package features;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Transaction extends BaseFeatures{

    @AndroidFindBy(id="org.gnucash.android:id/menu_save")
    private MobileElement saveTrxBtn;

    @AndroidFindBy(id="org.gnucash.android:id/input_transaction_name")
    private MobileElement trxNameInputBox;

    @AndroidFindBy(id="org.gnucash.android:id/input_transaction_amount")
    private MobileElement trxAmountInputBox;

    @AndroidFindBy(id="org.gnucash.android:id/fab_create_transaction")
    private MobileElement addTransactionBtn;

    @AndroidFindBy(xpath = "//*[@resource-id='org.gnucash.android:id/transaction_recycler_view']/android.widget.FrameLayout")
    private List<MobileElement> listOfTransaction;

    By primaryText=  MobileBy.id( "org.gnucash.android:id/primary_text");
    By secondaryText=  MobileBy.id( "org.gnucash.android:id/secondary_text");
    By trxText=  MobileBy.id( "org.gnucash.android:id/transaction_amount");


    public Transaction addTransaction(String trxName, String trxAmount){
        addTransactionBtn.click();
        trxNameInputBox.setValue(trxName);
        trxAmountInputBox.setValue(trxAmount);
        saveTrxBtn.click();
        return this;
    }

    public Transaction verifyTransactionDetail(String trxName,String trxAmount){
        assertThat(listOfTransaction.get(0)
                .findElement(primaryText).getText()).isEqualTo(trxName);

        assertThat(listOfTransaction.get(0)
                .findElement(trxText).getText()).contains(trxAmount);

        return this;
    }


    public Transaction(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
}
