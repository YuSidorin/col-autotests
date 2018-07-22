package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoresiteHelper extends BaseHelper {

    public static String Email = AccountHelper.companyName + "@cbsi.com";
    public static String StoreName = "";
    public static final String Password = "Qwe123456789";

    public StoresiteHelper(WebDriver wd) {
        super(wd);
    }

    public void createTempEmail() {

        wd.get("https://temp-mail.org/ru/option/change/");
//        wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        try {
        sleep(2);
        type(By.cssSelector("input.form-control"), AccountHelper.companyName);
        click(By.cssSelector("#postbut"));
        StoresiteHelper.Email = wd.findElement(By.cssSelector(".mail")).getAttribute("value");
//        waitForElementToBeVisible(By.cssSelector("#postbut"));
//        } catch (TimeoutException ignore) {

    }

    public void createAccount(String email, String firstName, String lastName, String workPhone, String address, String city) {
        wd.get("https://stage.channelonline.com/colqa_sanity/forsanity/Login/main");
        click(By.linkText("Create An Account"));
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#firstName"), firstName);
        type(By.cssSelector("#lastName"), lastName);
        type(By.cssSelector("#workPhone"), workPhone);
        type(By.cssSelector("#address"), address);
        type(By.cssSelector("#city"), city);
        click(By.cssSelector("#signup-btn"));
        waitForElementToBeVisible(By.cssSelector("input[name=Save]"));
        type(By.cssSelector("#password1"), StoresiteHelper.Password);
        type(By.cssSelector("#password2"), StoresiteHelper.Password);
        click(By.cssSelector("input[name=Save]"));
        alertOk();
    }

    public void login() {
        waitForElementToBeVisible(By.cssSelector("input[name=login-btn]"));
        type(By.cssSelector("#credential_0"), StoresiteHelper.Email);
        type(By.cssSelector("#credential_1"), StoresiteHelper.Password);
        click(By.cssSelector("input[name=login-btn]"));
        waitForElementToBeVisible(By.cssSelector("#topnav-account-toggle"));
        StoreName = wd.findElement(By.cssSelector("#topnav-account-toggle")).getText();
    }
}
