package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }
    public void login(String username, String password) {
        type(By.cssSelector("input#credential_0"), username);
        type(By.cssSelector("input#credential_1"),password);
        click(By.cssSelector("input[name=login-btn]"));
    }
}
