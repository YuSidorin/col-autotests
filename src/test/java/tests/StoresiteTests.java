package tests;

import appmanager.AccountHelper;
import appmanager.StoresiteHelper;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;


public class StoresiteTests extends TestBase {

    @Test
    public void createStoresiteAccount() {
        app.storesite().goToStoresite("forsanity");
        app.storesite().createAccount(StoresiteHelper.Email, AccountHelper.firstName, AccountHelper.lastName, AccountHelper.phone, AccountHelper.address, AccountHelper.city, AccountHelper.zip);
        app.storesite().login();
        assertThat(StoresiteHelper.StoreName, containsStringIgnoringCase(AccountHelper.companyName));

    }

    @Test
    public void createQuote() {
        app.storesite().goToStoresite("forsanity");
        app.storesite().login();
        app.storesite().setOfficemaxOrderNumberIfPresent();
        app.storesite().addProducts("Lenovo", 1, 4, 9);
        app.storesite().checkout();

        assertThat();
    }

}
