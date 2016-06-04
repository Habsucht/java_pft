package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;

public class GroupBasicTestsMethod extends BaseTestsMethod {

    protected void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    protected void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }
}
