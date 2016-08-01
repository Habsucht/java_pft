/**
 *  The class implements the communication with selenium
 */
package ru.stqa.pft.mantis.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ru.stqa.pft.mantis.appmanager.service.*;
import ru.stqa.pft.mantis.data.UserData;

public class ApplicationManager {
    private final Properties properties;
    public String webBaseUrl;
    public UserData admin;

    private NavigationHelper navigationHelper;
    private BaseHelper baseHelper;

    private DbHelper dbHelper;
    private SoapHelper soapHelper;
    private FtpSessionHelper ftpSessionHelper;
    private MailHelper mailHelper;

    private WebDriver wd;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    // Method retrieves the address
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        admin = new UserData("administrator", properties.getProperty("web.loginAdmin"), properties.getProperty("web.passwordAdmin"), null);
    }

    public WebDriver initBrowser() {
        if (wd == null ) {
            // Check to run a browser
            switch (browser) {
                case BrowserType.FIREFOX:
                    wd = new FirefoxDriver();
                    break;
                case BrowserType.CHROME:
                    wd = new ChromeDriver();
                    break;
                case BrowserType.IE:
                    wd = new InternetExplorerDriver();
                    break;
            }

            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            webBaseUrl = properties.getProperty("web.baseUrl");
            wd.get(properties.getProperty("web.baseUrl"));

            baseHelper = new BaseHelper(wd);
            navigationHelper = new NavigationHelper(wd);
        }

        return wd;
    }

    public void stop() {
        if (wd != null) { wd.quit(); }
    }

    public static boolean isAlertPresent(WebDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public BaseHelper getBaseHelper() {
        return baseHelper;
    }

    public DbHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }

    public HttpSessionHelper newSession() {
        return new HttpSessionHelper(this);
    }

    public FtpSessionHelper getFtpSessionHelper() {
        if (ftpSessionHelper == null) {
            ftpSessionHelper = new FtpSessionHelper(this);
        }
        return ftpSessionHelper;
    }

    public MailHelper getMailHelper() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper getSoapHelper() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}