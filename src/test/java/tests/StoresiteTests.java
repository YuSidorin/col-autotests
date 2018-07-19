package tests;

import appmanager.AccountHelper;
import appmanager.StoresiteHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoresiteTests extends TestBase {

    @Test
    public void createStoresiteAccount() {
        app.storesite().createTempEmail();
        app.storesite().createAccount(StoresiteHelper.Email, AccountHelper.firstName, AccountHelper.lastName, AccountHelper.phone, AccountHelper.address, AccountHelper.city);
        Assert.assertEquals(app.storesite().login(), AccountHelper.companyName);

    }

}
