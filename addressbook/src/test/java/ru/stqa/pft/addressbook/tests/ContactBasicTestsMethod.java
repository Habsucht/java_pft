package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;

public class ContactBasicTestsMethod extends BaseTestsMethod {

    protected void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    protected void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }
}
