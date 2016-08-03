package ru.stqa.pft.mantis.tests.users;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.data.UserData;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.tests.BaseTests;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends BaseTests {
    private Users users;

    @BeforeTest
    public void ensurePrecondition() {
        app.getMailHelper().start();
        app.initBrowser();

        // Get a list of all existing users
        users = app.getDbHelper().getUserFromDb();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException, SQLException {
        // Login by Administrator
        app.getBaseHelper().type(By.name("username"), app.admin.getLogin());
        app.getBaseHelper().type(By.name("password"), app.admin.getPassword());
        app.getBaseHelper().click(By.cssSelector("input[value='Войти']"));

        // Go to user administration page
        app.getNavigationHelper().gotoManageUserPage();

        // Receiving a random user from the list
        UserData user = users.iterator().next();

        // Go to the edit user account
        app.getBaseHelper().click(By.linkText(user.getLogin()));
        app.getBaseHelper().click(By.cssSelector("input[value='Сбросить пароль']"));

        // Receiving emails
        List<MailMessage> mailMessages = app.getMailHelper().waitForMail(2, 100000);

        // Search links for access to emails
        String link = findConfirmationLink(mailMessages, user.getEmail());

        // Jump to page registration confirmation
        app.getNavigationHelper().gotoPage(link);

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
