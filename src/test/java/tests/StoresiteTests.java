package tests;

import appmanager.StoresiteHelper;
import org.testng.annotations.Test;

public class StoresiteTests extends TestBase {

    @Test
    public void createStoresiteAccount() {
        app.storesite().createTempEmail();
        app.storesite().createAccount(StoresiteHelper.Email);
//        assertEquals(quoteNumber, quoteNumberAfter);
    }

}
