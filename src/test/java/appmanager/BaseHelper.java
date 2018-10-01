package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseHelper {
    WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public Select select;
    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void clickTwice(By locator) {
        wd.findElement(locator).click();
        wd.findElement(locator).click();
    }
    public void alertOk() {
        waitForNewWindow();
        Alert alert = wd.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);

    }

    public void addProducts(String product, int... checkboxes) {
        searchProduct(product);
        waitForElementToLoad("#search_form");
        checkCompareBoxes(checkboxes);
        click(By.cssSelector("#nextaction-caret"));
        click(By.cssSelector("#lineaction-addtodoc"));
    }

    public void checkCompareBoxes(int... nThCheckBoxes) {
        List<WebElement> checkBoxes = wd.findElements(By.cssSelector("input[class='check-multiple']"));
        for (int n : nThCheckBoxes) {
            checkBoxes.get(n - 1).click();
        }
    }

    public void setOfficemaxOrderNumberIfPresent() {
        if (isElementPresent(By.cssSelector("#proposalCustomField_420"))) {
            click(By.cssSelector("#proposalCustomField_420"));
            click(By.cssSelector("td.ui-datepicker-days-cell-over.ui-datepicker-today > a"));
        } else {
        }
    }

    public void searchProduct(String productName) {
        waitForElementToBeVisible(By.cssSelector("input#addProductKeyword"));
        wd.findElement(By.cssSelector("input#addProductKeyword")).sendKeys(productName);
        wd.findElement(By.cssSelector("button#add-product-submit")).click();
    }

    public Select getSelect(WebElement element) {
        select = new Select(element);
        return select;
    }

    public void goToAccounts() {
        waitForElementToLoad("#tab-customers");
        click(By.xpath("//*[@id=\"tab-customers\"]"));
    }

    public void switchToNextTab() {
        wd.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
        ArrayList<String> tabs = new ArrayList<String>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public String cleanInt(String text) {
        return text.replaceAll("\\D", "").replaceAll("[-().]", "");

    }

    public void waitForElementToLoad(String element) {
        new WebDriverWait(wd, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element)));
    }

    public void waitForElementToBeVisible(By by) {
        waitForElementToBeVisible(by, 15);
    }

    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForNewWindow() {
        new WebDriverWait(wd, 10).until((wd) -> wd.getWindowHandles().size() > 1);
    }
    public void waitForElementToBeVisible(By by, long timeInSeconds) {
        new WebDriverWait(wd, timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void waitForQuickLoad(int second) {
        try {
            waitForElementToBeVisible(By.cssSelector("div#loading-modal"), second);

        } catch (Exception e) {

        }
    }
}
