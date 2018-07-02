package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QuoteHelper extends BaseHelper {


    public QuoteHelper(WebDriver wd) {
        super(wd);
    }

    public void goToCreate() {
        waitForElementToLoad("#tab-customers");
        click(By.xpath("//*[@id=\"tab-customers\"]"));
        click(By.linkText("Current Account"));
        click(By.linkText("Create Quote"));
    }

    public void addProducts() {
        searchProduct("Lenovo");
        waitForElementToLoad("#search_form");
        checkCompareBoxes(1, 4, 9);
        click(By.cssSelector("#nextaction-caret"));
        click(By.cssSelector("#lineaction-addtodoc"));
    }

    public void assertTab(String tabName, String tabText) {
        wd.findElement(By.id(tabName)).getText();
    }

    public void TabClick() {
        click(By.xpath("//*[@id=tab-customers]/span"));
    }

    public QuoteHelper checkCompareBoxes(int... nThCheckBoxes) {
        List<WebElement> checkBoxes = wd.findElements(By.cssSelector("input[class='check-multiple']"));

        for (int n : nThCheckBoxes) {
            checkBoxes.get(n - 1).click();
        }
        return this;
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

//        logger.info(quoteInt + "");
        return quoteInt;
    }

    public QuoteHelper searchProduct(String productName) {
        waitForElementToBeVisible(By.cssSelector("input#addProductKeyword"));
        wd.findElement(By.cssSelector("input#addProductKeyword")).sendKeys(productName);
        wd.findElement(By.cssSelector("button#add-product-submit")).click();
        return this;
    }

    public void waitForPageToLoad() {
        new WebDriverWait(wd, 30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#quoteNumber")));
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}