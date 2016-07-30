/**
 *  A class to test the login user
 */

package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.service.HttpSessionHelper;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests{
    @Test
    public void testLogin() throws IOException {
        HttpSessionHelper session = app.newSession();

        assertTrue(session.login(app.admin.getLogin(), app.admin.getPassword()));
        assertTrue(session.isLoggedInAs(app.admin.getLogin()));
    }
}
