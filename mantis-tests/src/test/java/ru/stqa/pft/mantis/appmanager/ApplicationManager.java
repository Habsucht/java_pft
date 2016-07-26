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

public class ApplicationManager {
    private final Properties properties;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;

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

            wd.get(properties.getProperty("web.baseUrl"));

            sessionHelper = new SessionHelper(wd);
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

    public HttpSession newSession() {
        return new HttpSession(this);
    }
}