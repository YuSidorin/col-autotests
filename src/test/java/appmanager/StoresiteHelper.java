package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoresiteHelper extends BaseHelper {

    public static String Email = "";
    public static String Password = "Qwe123456789";

    public StoresiteHelper(WebDriver wd) {
        super(wd);
    }

    public void createTempEmail() {
        wd.get("https://temp-mail.org/ru/option/change/");
        type(By.cssSelector("input.form-control"), AccountHelper.companyName);
        click(By.cssSelector("#postbut"));
        StoresiteHelper.Email = wd.findElement(By.cssSelector(".mail")).getAttribute("value");
        waitForElementToBeVisible(By.cssSelector("#postbut"));
    }

    public void createAccount(String Email, String firstName, String lastName, String workPhone, String address, String city) {
        wd.get("https://stage.channelonline.com/acme/alextest1/Login/");
        click(By.linkText("Create An Account"));
        type(By.cssSelector("#email"), Email);
        type(By.cssSelector("#firstName"), firstName);
        type(By.cssSelector("#lastName"), lastName);
        type(By.cssSelector("#workPhone"), workPhone);
        type(By.cssSelector("#address"), address);
        type(By.cssSelector("#city"), city);
        click(By.cssSelector("#signup-btn"));
        waitForElementToBeVisible(By.cssSelector("input[name=Save]"));

        click(By.cssSelector("input[name=Save]"));
        alertOk();
    }

    public String login() {
        type(By.cssSelector("#credential_0"), StoresiteHelper.Email);
        type(By.cssSelector("#credential_1"), StoresiteHelper.Password);
        click(By.cssSelector("input[name=login-btn]"));
        waitForElementToBeVisible(By.cssSelector("#topnav-account-toggle"));
        return wd.findElement(By.cssSelector("#topnav-account-toggle")).getText();
    }
}
