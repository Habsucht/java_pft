package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.data.UserData;

public class RegistrationTests extends BaseTests{
    @BeforeTest
    public void ensurePrecondition() {
        app.initBrowser();

        app.getNavigationHelper().gotoRegistrationPage();
    }

    @Test
    public void testRegistration() {
        UserData user = new UserData();

        app.getBaseHelper().type(By.name("username"), user.getUserName());
        app.getBaseHelper().type(By.name("email"), user.getEmail());
        app.getBaseHelper().click(By.cssSelector("input[value='Зарегистрироваться']"));
    }
}
