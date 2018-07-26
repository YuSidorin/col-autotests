package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QuoteHelper extends BaseHelper {

    public QuoteHelper(WebDriver wd) {
        super(wd);
    }

    public void goToCreate() {
        waitForElementToLoad("#tab-customers");
        click(By.xpath("//*[@id=\"tab-customers\"]"));
        getSelect(wd.findElement(By.cssSelector("#accountType")));
        select.selectByValue("Customer");
        waitForElementToBeVisible(By.cssSelector("a[title ='View Customer']"));
        clickTwice(By.cssSelector("a[title ='View Customer']"));
        waitForElementToBeVisible(By.linkText("Create Quote"));
        click(By.linkText("Create Quote"));
    }


    public int getDocNumber() {
        int quoteInt;
        try {
            String quoteNumber = wd.findElement(By.cssSelector("div#quoteNumber")).getText();
            quoteNumber = quoteNumber.split("-")[0].trim();
            quoteInt = Integer.parseInt(quoteNumber);
        } catch (NumberFormatException e) {
            String quoteNumber = wd.findElement(By.cssSelector("input#quoteNumber")).getAttribute("value");
            quoteInt = Integer.parseInt(quoteNumber);
        }
        return quoteInt;
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}