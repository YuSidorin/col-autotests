package tests;

import appmanager.AccountHelper;
import org.testng.annotations.Test;

public class AccountsTests extends TestBase {

    @Test
    public void createCustomer() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Customer);
        app.account().fillAccountForm();
//        assertTrue(recentAccountsTab.hasCompany(companyName));

    }
}
