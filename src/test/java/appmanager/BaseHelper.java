package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {
    WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);

    }


    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void waitForElementToLoad(String element) {
        new WebDriverWait(wd, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element)));
    }

    public void waitForElementToBeVisible(By by) {
        waitForElementToBeVisible(by, 30);
    }

    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(wd, 5).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeVisible(By by, long timeInSeconds) {
        new WebDriverWait(wd, timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void forceWait(long time) {
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
