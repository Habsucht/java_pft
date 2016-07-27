package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

public class RegistrationTests extends BaseTests{
    private static ApplicationManager app;
    private WebDriver wd;

    @Test
    public void testRegistration() {
        this.app = BaseTests.app;
        wd = app.initBrowser();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");

        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("email")).sendKeys(email);

        wd.findElement(By.cssSelector("input[value='Signup']")).click();
    }
}
