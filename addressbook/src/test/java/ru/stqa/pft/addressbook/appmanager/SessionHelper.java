package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.data.LoginData;

public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void logon(LoginData loginData) {
        type(By.name("user"), loginData.getUserName());
        type(By.name("pass"), loginData.getPassword());

        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
