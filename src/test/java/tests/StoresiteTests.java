package tests;

import appmanager.AccountHelper;
import appmanager.StoresiteHelper;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class StoresiteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.storesite().goToStoresiteAdmin();
        if (!app.storesite().verifyIsElementInList(By.cssSelector("td[class=tablecell-name]"), app.properties.getProperty("storesite.name"))) {
            if (app.storesite().isElementPresent(By.cssSelector("a.disabled"))) {
//                app.storesite().deleteAllStoresites();
                app.storesite().createNewStoresite(app.properties.getProperty("storesite.name"));
            } else app.storesite().createNewStoresite(app.properties.getProperty("storesite.name"));
        }
    }

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
        app.storesite().goToStoresiteAdmin(app.properties.getProperty("storesite.name"));
        app.storesite().click(By.cssSelector("div.btn-toolbar > button"));
        String changedstore = app.properties.getProperty("storesite.name") + "_new";
        app.storesite().waitForElementToLoad("#storesite_name");
        app.storesite().type(By.id("storesite_name"), changedstore);
        app.storesite().click(By.cssSelector("button.btn.btn-primary.save-panel"));
        app.storesite().goToStoresiteAdmin();
        assertTrue(app.storesite().verifyIsElementInList(By.cssSelector("td[class=tablecell-name]"), changedstore));
    }



}
