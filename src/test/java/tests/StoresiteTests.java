package tests;

import appmanager.AccountHelper;
import appmanager.StoresiteHelper;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;


public class StoresiteTests extends TestBase {

    @Test
    public void createStoresite() {
        app.storesite().goToStoresiteAdmin();
        app.storesite().createNewStoresite();
        assertThat(StoresiteHelper.CREATEDSTORESITENAME, containsStringIgnoringCase(StoresiteHelper.STORESITENAME));
    }

    @Test
    public void deleteAllStoresites() {
        app.storesite().goToStoresiteAdmin();
        app.storesite().deleteAllStoresites();
        if (StoresiteHelper.DELETEDSTORESITENAME != "")
            assertFalse(app.storesite().isStoresiteDeleted(StoresiteHelper.DELETEDSTORESITENAME));
        else System.out.println("No storesite to delete");
    }

    @Test
    public void createStoresiteAccount() {
        app.storesite().goToStoresite(app.properties.getProperty("storesite.name"));
        app.storesite().createAccount(StoresiteHelper.Email, AccountHelper.firstName, AccountHelper.lastName, AccountHelper.phone, AccountHelper.address, AccountHelper.city, AccountHelper.zip);
        app.storesite().login();
        assertThat(StoresiteHelper.StoreName, containsStringIgnoringCase(AccountHelper.companyName));

    }

    @Test
    public void createQuote() {
        app.storesite().goToStoresite(app.properties.getProperty("storesite.name"));
        app.storesite().login();
        app.storesite().setOfficemaxOrderNumberIfPresent();
        app.storesite().addProducts("Lenovo", 1, 4, 9);
        app.storesite().checkout();

    }

    @Test
    public void verifyMessagingOnStoreAdmin() {
        app.storesite().goToStoresiteAdmin();

    }


}
