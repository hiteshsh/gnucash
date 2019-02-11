package tests;

import features.AccountSetup;
import features.Transaction;
import org.testng.annotations.Test;

public class TransactionTest extends BaseTest{

    @Test
    public void userShouldBeAbleToAddSalaryUnderIncome(){
        AccountSetup account= new AccountSetup(driver);
        account.shouldShowWelcomeMessage();
        account.setUpAccount("EUR","Create default accounts","Disable crash reports")
                .dismissUpdatesAlert()
                .verifyDefaultAccountDetails()
                .selectAccount("Income")
                .selectAccount("Salary");

        Transaction transaction= new Transaction(driver);
        transaction.addTransaction("Office Salary","1000.00")
                .verifyTransactionDetail("Office Salary","1,000.00");

    }
}
