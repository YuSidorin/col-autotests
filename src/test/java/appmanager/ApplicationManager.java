package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;
    private SessionHelper sessionHelper;
    private QuoteHelper quoteHelper;
    private AccountHelper accountHelper;
    private StoresiteHelper storesiteHelper;

    private String chromeDriverVersion = System.getProperty("chromedriver-version", "2.40");

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

//        if (Objects.equals(browser, BrowserType.FIREFOX)) {
//            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Tools/firefox.exe"));
//        } else if (Objects.equals(browser, BrowserType.CHROME)) {
//            wd = new ChromeDriver(new ChromeOptions().setBinary("D:/Work/Channel_Online/col-autotests/src/test/resources/chromedriver/chromedriver.exe"));
//        } else if (Objects.equals(browser, BrowserType.IE)) {
//            wd = new InternetExplorerDriver();
//        }
//
        wd = configureDrivers();
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper = new SessionHelper(wd);
        quoteHelper = new QuoteHelper(wd);
        accountHelper = new AccountHelper(wd);
        storesiteHelper = new StoresiteHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        wd.manage().window().maximize();
    }

    public void stop() {
        wd.quit();
    }

    public String getBrowser() {
        return browser;
    }

    public WebDriver configureDrivers() {
        WebDriver emptyDriver = null;

        if (getBrowser().contains("chrome")) {
            emptyDriver = getChromeDriver();

        }
        return emptyDriver;
    }

    public WebDriver getChromeDriver() {
        String pathToChromeDriver = System.getProperty("user.dir") + "/src/test/resources/" + chromeDriverVersion + "/";
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("mac")) {
            pathToChromeDriver = pathToChromeDriver + "chromedriver_mac32";
        } else if (osName.toLowerCase().contains("windows")) {
            pathToChromeDriver = pathToChromeDriver + "chromedriver.exe";
        } else {
            pathToChromeDriver = pathToChromeDriver + "chromedriver_linux32";
            //pathToChromeDriver = "/usr/local/bin/chromedriver_linux32";
        }

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        return new ChromeDriver();
    }

    public QuoteHelper quote() {
        return quoteHelper;
    }

    public AccountHelper account() {
        return accountHelper;
    }

    public StoresiteHelper storesite() {
        return storesiteHelper;
    }
}


