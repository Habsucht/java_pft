/**
 *  The class implements the authentication online
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.data.LoginData;

class SessionHelper extends BaseHelper {

    SessionHelper(WebDriver wd) {
        super(wd);
    }

    void logon(LoginData loginData) {
        type(By.name("user"), loginData.getUserName());
        type(By.name("pass"), loginData.getPassword());

        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
