package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.data.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        if (!wd.findElement(By.id("2")).isSelected())
        click(By.id("2"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());

        type(By.name("nickname"), contactData.getNickName());

        type(By.name("company"), contactData.getCompanyName());

        contactBirthday(contactData);

        type(By.name("home"), contactData.getHomePhoneNumber());
    }

    private void contactBirthday(ContactData contactData) {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[9]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[9]"));
        }

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[7]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[7]"));
        }

        type(By.name("byear"), contactData.getBirthdayYear);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }
}
