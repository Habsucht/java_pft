package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.data.LoginData;

public class SessionHelper extends BaseHelper {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void logon(LoginData loginData) {
        type(By.name("user"), loginData.getUserName());
        type(By.name("pass"), loginData.getPassword());

        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
