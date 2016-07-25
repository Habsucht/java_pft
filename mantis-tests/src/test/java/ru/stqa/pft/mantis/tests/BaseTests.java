/**
 *  The basic test class for initialization and termination
 */

package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.mantis.appmanager.ApplicationManager;

public class BaseTests {

    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public static void setUp() throws Exception {
        app.init();
    }


    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}