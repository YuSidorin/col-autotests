package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainPageTests extends TestBase {

    @Test
    public void createQuote() {
        app.quote().goToCreate();
        int quoteNumber = app.quote().getDocNumber();
        app.quote().addProducts();
        app.quote().sleep(1000);
        int quoteNumberAfter = app.quote().getDocNumber();
        assertEquals(quoteNumber, quoteNumberAfter);
    }

}

