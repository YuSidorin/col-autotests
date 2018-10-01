package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoresiteHelper extends BaseHelper {

    public static String Email = AccountHelper.companyName + "@cbsi.com";
    public static String StoreName = "";
    public static final String PASSWORD = "Qwe123456789";
    public static final String STORESITENAME = "" + System.currentTimeMillis();
    public static String CREATEDSTORESITENAME = "";
    public static String DELETEDSTORESITENAME = "";

    public StoresiteHelper(WebDriver wd) {
        super(wd);
    }

    public void goToStoresite(String name) {
        click(By.cssSelector("a[title=StoreSites]"));
        click(By.linkText(name));
        switchToNextTab();
    }

    public void goToStoresiteAdmin() {
        click(By.cssSelector("a[id=crm-controlpanesectionlink-admin]"));
        click(By.cssSelector("a[id=crm-controlpanelink-storesite]"));
    }

    public void createNewStoresite() {
        int i = +1;
        String storesitename = StoresiteHelper.STORESITENAME + i;
        click(By.linkText("Create New Store"));
        click(By.cssSelector("input[name=active]"));
        click(By.cssSelector("label[for=store_type_public]"));
        type(By.cssSelector("#storesite_url"), storesitename);
        type(By.cssSelector("#storesite_name"), storesitename);
        click(By.cssSelector("button[title=Save]"));
        waitForElementToBeVisible(By.linkText("https://stage.channelonline.com/colqa_sanity/" + storesitename));
        CREATEDSTORESITENAME = wd.findElement(By.cssSelector("h1[class=page-title]")).getText();
    }

    public void deleteAllStoresites() {
        int maxstorenumber = Integer.parseInt(cleanInt(wd.findElement(By.cssSelector("#crm-main-pane-body > div > div > strong")).getText()));
        List<WebElement> storesites = wd.findElements(By.cssSelector("td[class=tablecell-name]"));
        int currentstorenumber = storesites.size();
        while (currentstorenumber > maxstorenumber - 1) {
            DELETEDSTORESITENAME = wd.findElement(By.cssSelector("td[class=tablecell-name]")).getText();
            click(By.cssSelector("a[class=\"storesite-delete \"]"));
            alertOk();
            waitForElementToBeVisible(By.cssSelector("td[class=tablecell-name]"));
        }

    }

    public boolean isStoresiteDeleted(String name) {
        List<WebElement> elements = wd.findElements(By.cssSelector("td[class=tablecell-name]"));
        for (WebElement e : elements) {
            if (e.getText().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        System.out.println(elements);
        return false;
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
        waitForElementToBeVisible(By.cssSelector("#password1"));
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
