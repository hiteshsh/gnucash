package tests;

import features.AccountSetup;
import org.testng.annotations.Test;

public class AccountSetupTest extends BaseTest{

    @Test
    public void userShouldBeAbleToSetUpAccount(){
        AccountSetup account= new AccountSetup(driver);
        account.shouldShowWelcomeMessage();
        account.setUpAccount("EUR","Create default accounts","Disable crash reports")
                .dismissUpdatesAlert()
                .verifyDefaultAccountDetails();

    }


}
