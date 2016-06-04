package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.data.LoginData;

public class SessionHelper {
    private FirefoxDriver wd;
    
    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void logon(LoginData loginData) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(loginData.getUserName());
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(loginData.getPassword());
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
}
