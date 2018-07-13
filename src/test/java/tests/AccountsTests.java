package tests;

import appmanager.AccountHelper;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AccountsTests extends TestBase {


    @Test
    public void createCustomer() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Customer);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }

    @Test
    public void createLead() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Lead);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }

    @Test
    public void createProspect() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Prospect);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }

    @Test
    public void createPartner() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Partner);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }

    @Test
    public void createVendor() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Vendor);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }

    @Test
    public void createGeneric() {
        app.account().goToAccounts();
        app.account().createAccount(AccountHelper.AccountType.Generic);
        app.account().fillAccountForm();
        assertEquals(app.account().getCompanyName(), AccountHelper.companyName);
    }
}
