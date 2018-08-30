package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoresiteHelper extends BaseHelper {

    public static String Email = AccountHelper.companyName + "@cbsi.com";
    public static String StoreName = "";
    public static final String PASSWORD = "Qwe123456789";

    public StoresiteHelper(WebDriver wd) {
        super(wd);
    }

    public void goToStoresite(String name) {
        click(By.cssSelector("a[title=StoreSites]"));
        click(By.linkText(name));
        switchToNextTab();
    }

    public void createAccount(String email, String firstName, String lastName, String workPhone, String address, String city, String zip) {
//        wd.get("https://stage.channelonline.com/colqa_sanity/forsanity/Login/main");
        click(By.cssSelector("#topnav-login"));
        click(By.linkText("Create An Account"));
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#firstName"), firstName);
        type(By.cssSelector("#lastName"), lastName);
        type(By.cssSelector("#workPhone"), workPhone);
        type(By.cssSelector("#address"), address);
        type(By.cssSelector("#city"), city);
        type(By.cssSelector("#zip"), zip);
        click(By.cssSelector("#signup-btn"));
        waitForElementToBeVisible(By.cssSelector("input[name=Save]"));
        type(By.cssSelector("#password1"), StoresiteHelper.PASSWORD);
        type(By.cssSelector("#password2"), StoresiteHelper.PASSWORD);
        click(By.cssSelector("input[name=Save]"));
        alertOk();
    }

    public void login() {
        waitForElementToBeVisible(By.cssSelector("#topnav-login"));
        click(By.cssSelector("#topnav-login"));
        type(By.cssSelector("#credential_0"), StoresiteHelper.Email);
        type(By.cssSelector("#credential_1"), StoresiteHelper.PASSWORD);
        click(By.cssSelector("input[name=login-btn]"));

        waitForElementToBeVisible(By.cssSelector("#topnav-account-toggle"));
        StoreName = wd.findElement(By.cssSelector("#topnav-account-toggle")).getText();
    }

    public void checkout() {
        click(By.cssSelector("#next-action_checkoutCart"));
        type(By.cssSelector("#ponumber"), "1234567890");
        click(By.cssSelector("#payment-type-cod"));
        click(By.cssSelector("#btn-paymentdelivery-save"));
        click(By.cssSelector("#btn-billingshipping-next"));
        click(By.cssSelector("#next-action_checkoutAgreed"));
    }

    public void search() {
        waitForElementToBeVisible(By.cssSelector("#topnav-login"));
        searchProduct("Lenovo");
        click(By.cssSelector("input[name=login-btn]"));
        waitForElementToBeVisible(By.cssSelector("#topnav-account-toggle"));
        StoreName = wd.findElement(By.cssSelector("#topnav-account-toggle")).getText();
    }


    public void createTempEmail() {
        wd.get("https://temp-mail.org/ru/option/change/");
        sleep(2);
        type(By.cssSelector("input.form-control"), AccountHelper.companyName);
        click(By.cssSelector("#postbut"));
        StoresiteHelper.Email = wd.findElement(By.cssSelector(".mail")).getAttribute("value");
    }
}
