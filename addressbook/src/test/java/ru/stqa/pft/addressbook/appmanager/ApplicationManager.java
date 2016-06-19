/**
 *  The class implements the communication with selenium
 */
package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import ru.stqa.pft.addressbook.data.LoginData;

public class ApplicationManager {
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;

    private WebDriver wd;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
        //Check to run a browser
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

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        wd.get("http://localhost/addressbook/");

        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);

        sessionHelper.logon(new LoginData ("admin", "secret"));
    }

    public void stop() {
        wd.quit();
    }

    public static boolean isAlertPresent(WebDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}