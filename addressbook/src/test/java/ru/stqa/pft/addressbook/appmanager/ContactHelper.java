package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.data.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int numContact) {
        if (!wd.findElement(By.id(String.valueOf(numContact))).isSelected())
        click(By.id(String.valueOf(numContact)));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstName);
        type(By.name("lastname"), contactData.lastName);

        type(By.name("nickname"), contactData.nickName);

        type(By.name("company"), contactData.companyName);

        type(By.name("home"), contactData.homePhoneNumber);

        if (isElementPresent(By.name("new_group"))) {
            String group = contactData.getGroup();
            if (wd.findElement(By.name("new_group")).getText().equals(group)) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(group);
            } else {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
            }
        }

        contactBirthday(contactData);
    }

    private void contactBirthday(ContactData contactData) {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.birthdayDay + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.birthdayDay + "]"));
        }

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.birthdayMonth + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.birthdayMonth + "]"));
        }

        type(By.name("byear"), String.valueOf(contactData.birthdayYear));
    }

    public void submitContactCreation() {
        submitModification();
        //click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void editContact(int numContact) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + numContact + "]/td[8]/a/img"));
    }

    public void updateContact() {
        click(By.name("update"));
        //click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }
}
