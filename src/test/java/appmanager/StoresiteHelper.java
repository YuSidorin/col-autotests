package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoresiteHelper extends BaseHelper {

    public static String Email = "";
    public StoresiteHelper(WebDriver wd) {
        super(wd);
    }



    public void createTempEmail() {
        wd.get("https://temp-mail.org/ru/option/change/");
        type(By.cssSelector("input.form-control"), AccountHelper.companyName);
        click(By.cssSelector("#postbut"));
        StoresiteHelper.Email = wd.findElement(By.cssSelector(".mail")).getAttribute("value");
    }

    public void createAccount(String Email) {
        wd.get("https://stage.channelonline.com/acme/alextest1/Login/");
        type(By.cssSelector("#email"), Email);
    }
}
