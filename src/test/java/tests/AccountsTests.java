package tests;

import appmanager.AccountHelper;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AccountsTests extends TestBase {


    @Test
    public void createCustomer() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Customer);
        app.account().waitForElementToBeVisible(By.cssSelector("#company"));
        app.account().fillAccountForm();
        app.account().waitForElementToBeVisible(By.cssSelector("#detailsnameAndNumber > div"));
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
//        assert(Customer, AccountsSearch.Customer);

    }

    @Test
    public void createLead() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Lead);
        app.account().waitForElementToBeVisible(By.cssSelector("#lname"));
        app.account().fillAccountForm();

//        assertTrue(recentAccountsTab.hasCompany(companyName));

    }
}
