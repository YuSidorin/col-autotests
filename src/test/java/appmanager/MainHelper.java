package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainHelper extends BaseHelper {


    public MainHelper(WebDriver wd) {
        super(wd);
    }

    public void assertTab(String tabName, String tabText) {
        wd.findElement(By.id(tabName)).getText();
    }

    public void TabClick() {
        click(By.xpath("//*[@id=tab-customers]/span"));
    }

    public MainHelper checkCompareBoxes(int... nThCheckBoxes) {
        List<WebElement> checkBoxes = wd.findElements(By.cssSelector("input[class='check-multiple']"));

        for (int n : nThCheckBoxes) {
            checkBoxes.get(n - 1).click();
        }
        return this;
    }
    public MainHelper searchClick ()  {
        JavascriptExecutor jse = (JavascriptExecutor)wd;

        jse.executeScript("arguments[0].scrollIntoView()", "button#add-product-submit");
        return  this;
    }

    public long getDocNumber() {
        long quoteInt;
        try {
            String quoteNumber = wd.findElement(By.cssSelector("div#quoteNumber")).getText();
            quoteNumber = quoteNumber.split("-")[0].trim();
            quoteInt = Long.parseLong(quoteNumber);
        } catch (NumberFormatException e) {
            String quoteNumber = wd.findElement(By.cssSelector("input#quoteNumber")).getAttribute("value");
            quoteInt = Long.parseLong(quoteNumber);
        }

//        logger.info(quoteInt + "");
        return quoteInt;
    }

    public MainHelper searchProduct(String productName) {
        waitForElementToBeVisible(By.cssSelector("input#addProductKeyword"));
//        forceWait(500); // Scratchpad input does not recognize there is text without this wait.

        wd.findElement(By.cssSelector("input#addProductKeyword")).sendKeys(productName);
//        forceWait(500);
        wd.findElement(By.cssSelector("button#add-product-submit")).click();
//        try {
//            waitForQuickLoad(10);
//        } catch (Exception e) {
//
//        }
        return this;
    }
    public void switchFrame(String locator){
        wd.switchTo().defaultContent();
        waitForElementToBeVisible(By.cssSelector(String.valueOf(locator)));
        wd.switchTo().frame(wd.findElement(By.cssSelector(String.valueOf(locator))));
    }

}