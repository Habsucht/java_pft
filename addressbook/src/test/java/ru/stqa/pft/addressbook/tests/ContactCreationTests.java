package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.ContactData;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends BaseTestsMethod {

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Jon", "Dou", "void", "bigbox", "855614452266"));
        submitContactCreation();
    }

    protected void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    protected void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompanyName());

        contactBirthday();

        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHomePhoneNumber());
    }

    private void contactBirthday() {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[9]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[9]")).click();
        }

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[7]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[7]")).click();
        }

        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys("1956");
    }

    protected void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }
}
