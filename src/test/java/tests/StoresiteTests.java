package tests;

import org.testng.annotations.Test;

public class StoresiteTests extends TestBase {

    @Test
    public void createStoresiteAccount() {
        app.storesite().createTempEmail();
        app.storesite().createAccount(Email);
//        assertEquals(quoteNumber, quoteNumberAfter);
    }

}
