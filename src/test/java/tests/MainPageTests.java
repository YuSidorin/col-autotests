package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase {

    protected long quoteNumber;

    @Test
    public void tabAsserts() {

//     Assert.assertEquals( getText()., "accounts");

    }

    @Test
    public void createQuote() {
        app.tab().waitForElementToLoad("#tab-customers");
        app.tab().click(By.xpath("//*[@id=\"tab-customers\"]"));
        app.tab().click(By.linkText("Current Account"));
        app.tab().click(By.linkText("Create Quote"));
        quoteNumber = app.tab().getDocNumber();
        app.tab().searchProduct("Lenovo");
        app.tab().waitForElementToLoad("#search_form");
        app.tab().checkCompareBoxes(1,4,9);
        app.tab().click(By.cssSelector("#nextaction-caret"));
        app.tab().click(By.cssSelector("#lineaction-addtodoc"));

    }


}

