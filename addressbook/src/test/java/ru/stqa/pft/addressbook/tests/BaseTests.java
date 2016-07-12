/**
 *  The basic test class for initialization and termination
 */

package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class BaseTests {

    static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public static void setUp() throws Exception {
        app.init();
    }


    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}