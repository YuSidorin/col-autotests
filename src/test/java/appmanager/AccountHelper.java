package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountHelper extends BaseHelper {
    public static final String companyName = "QaCustomer" + System.currentTimeMillis();
    public static final String companyNameCommon = "QaCustomer";
    public static final String address = "666 Letnikovskaya St";
    public static final String city = "Moscow";
    public static final String zip = "115114";

    public AccountHelper(WebDriver wd) {
        super(wd);
    }

    public enum AccountType {
        Customer,
        Lead,
        Prospect,
        PARTNER,
        VENDOR,
        GENERIC
    }

    public void createAccount(AccountType accountType) {
        WebElement CreateAccount = wd.findElement(By.linkText("Create Account"));
        CreateAccount.click();
        waitForElementToLoad("#ctypec");
        WebElement Customer = wd.findElement(By.cssSelector("#ctypec"));
        WebElement Lead = wd.findElement(By.cssSelector("#ctypel"));
        WebElement Prospect = wd.findElement(By.cssSelector("#ctypep"));
        WebElement Partner = wd.findElement(By.cssSelector("#ctypepp"));
        WebElement Vendor = wd.findElement(By.cssSelector("#ctypev"));
        WebElement Generic = wd.findElement(By.cssSelector("#ctypeg"));
        WebElement OK = wd.findElement(By.cssSelector("save-tpl-btn"));
        if (accountType.equals("Customer")) {
            OK.click();
        } else if (accountType.equals("Lead")) {
            Lead.click();
        } else if (accountType.equals("Prospect")) {
            Prospect.click();
        } else if (accountType.equals("Partner")) {
            Partner.click();
        } else if (accountType.equals("Vendor")) {
            Vendor.click();
        } else if (accountType.equals("generic")) {
            Generic.click();

        }
        OK.click();
    }

    public void fillAccountForm() {
        setFirstName("Qa");
        setLastName("customers");
        setEmail(companyName + "@email.com");
        setContactInfo_CompanyName(companyName);
        clickSaveButton();
    }


}


