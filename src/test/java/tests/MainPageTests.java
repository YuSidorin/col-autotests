package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainPageTests extends TestBase {

    protected int quoteNumber;
    protected int quoteNumberAfter;


    @Test
    public void createQuote() {
        app.quote().waitForElementToLoad("#tab-customers");
        app.quote().click(By.xpath("//*[@id=\"tab-customers\"]"));
        app.quote().click(By.linkText("Current Account"));
        app.quote().click(By.linkText("Create Quote"));
        quoteNumber = app.quote().getDocNumber();
        app.quote().searchProduct("Lenovo");
        app.quote().waitForElementToLoad("#search_form");
        app.quote().checkCompareBoxes(1, 4, 9);
        app.quote().click(By.cssSelector("#nextaction-caret"));
        app.quote().click(By.cssSelector("#lineaction-addtodoc"));
        app.quote().click(By.cssSelector("#toggle-crm-doc-menu > span.caret.large"));
        app.quote().click(By.cssSelector("#dropmenu-content-crm-doc-menu > ul > li:nth-child(2) > a"));
        app.quote().sleep(1000);
        quoteNumberAfter = app.quote().getDocNumber();
        assertEquals(quoteNumber, quoteNumberAfter);
    }


}

