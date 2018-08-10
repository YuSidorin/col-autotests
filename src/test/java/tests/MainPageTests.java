package tests;

import appmanager.QuoteHelper;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainPageTests extends TestBase {

    @Test
    public void createQuote() {
        app.quote().create();
        assertEquals(QuoteHelper.quoteNumber, QuoteHelper.quoteNumberAfter);
    }

    @Test
    public void sendQuoteViaMail() {
        app.quote().create();
        app.quote().sendTo("email");
    }

}

