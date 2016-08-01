/**
 *  A class to test registration new user
 */

package ru.stqa.pft.mantis.tests.users;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.data.UserData;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.tests.BaseTests;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends BaseTests {
    @BeforeTest
    public void ensurePrecondition() {
        app.getMailHelper().start();
        app.initBrowser();

        app.getNavigationHelper().gotoRegistrationPage();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        UserData user = new UserData();
        System.out.println(user.toString());

        // Filling out the registration form (step one)
        app.getBaseHelper().type(By.name("username"), user.getLogin());
        app.getBaseHelper().type(By.name("email"), user.getEmail());
        app.getBaseHelper().click(By.cssSelector("input[value='Зарегистрироваться']"));

        // Receiving emails
        List<MailMessage> mailMessages = app.getMailHelper().waitForMail(2, 10000);

        // Jump to page registration confirmation
        app.getNavigationHelper().gotoPage(
                // Search links for access to emails
                findConfirmationLink(mailMessages, user.getEmail()));

        // Filling out the registration form (step two)
        app.getBaseHelper().type(By.name("realname"), user.getUserName());
        app.getBaseHelper().type(By.name("password"), user.getPassword());
        app.getBaseHelper().type(By.name("password_confirm"), user.getPassword());
        app.getBaseHelper().click(By.cssSelector("input[value='Изменить учетную запись']"));

        assertTrue(app.newSession().login(user.getLogin(), user.getPassword()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void ensurePostcondition() {
        app.getMailHelper().stop();
    }
}
