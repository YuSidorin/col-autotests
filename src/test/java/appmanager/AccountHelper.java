package appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class AccountHelper extends BaseHelper {
    public static final String companyName = "QaCustomer" + System.currentTimeMillis();
    public static String firstName = "QaCustomer";
    public static String lastName = "QaCustomer";
    public static final String address = "666 Letnikovskaya St";
    public static final String city = "Moscow";
    public static final String zip = "115114";

    private String accountType;

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
        WebElement Lead = wd.findElement(By.cssSelector("#ctypel"));
        WebElement Prospect = wd.findElement(By.cssSelector("#ctypep"));
        WebElement Partner = wd.findElement(By.cssSelector("#ctypepp"));
        WebElement Vendor = wd.findElement(By.cssSelector("#ctypev"));
        WebElement Generic = wd.findElement(By.cssSelector("#ctypeg"));
        this.accountType = accountType.toString().toLowerCase();
        if (this.accountType.equals("customer")) {
        } else if (this.accountType.equals("lead")) {
            Lead.click();
        } else if (this.accountType.equals("Prospect")) {
            Prospect.click();
        } else if (this.accountType.equals("Partner")) {
            Partner.click();
        } else if (this.accountType.equals("Vendor")) {
            Vendor.click();
        } else if (this.accountType.equals("generic")) {
            Generic.click();

        }
        click((By.cssSelector("#save-tpl-btn")));
    }

    public void fillAccountForm() {
        if (this.accountType.equals("lead")) {
            setFirstName("Qa");
            setLastName("customers");
            setEmail(companyName + "@email.com");
            clickUpdateButton();
            alertOk();
        } else {
            setCompanyName(companyName);
            clickSaveButton();
            alertOk();
        }
    }

    public void setFirstName(String firstName) {
        WebElement CompanyName = wd.findElement(By.cssSelector("#fname"));
        CompanyName.clear();
        CompanyName.sendKeys(companyName);

    }

    public void setLastName(String lastName) {
        WebElement CompanyName = wd.findElement(By.cssSelector("#lname"));
        CompanyName.clear();
        CompanyName.sendKeys(companyName);
    }

    public void setCompanyName(String companyName) {
        WebElement CompanyName = wd.findElement(By.cssSelector("#company"));
        CompanyName.clear();
        CompanyName.sendKeys(companyName);
    }

    public String getCompanyName() {
        WebElement CompanyName = wd.findElement(By.cssSelector("#detailsnameAndNumber > div"));
        String CompanyNameText = trim(CompanyName.toString());
        return CompanyNameText;
    }

    public void setEmail(String companyName) {
        WebElement CompanyName = wd.findElement(By.cssSelector("#email"));
        CompanyName.clear();
        CompanyName.sendKeys(companyName);
    }

    private void clickSaveButton() {
        click(By.cssSelector("#contact-finish-btn"));
    }

    private void clickUpdateButton() {
        click(By.cssSelector("#update-acct-btn"));
    }

    private void alertOk() {
        Alert alert = wd.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

    }


}


