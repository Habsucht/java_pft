/**
 *  The class implements the action with contacts
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.data.ContactData;

public class ContactHelper extends BaseHelper {

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int numContact) {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + numContact + "]/td[1]/input"));
    }

    public void fillContactForm(ContactData contactData) {
        if (!wd.findElement(By.name("firstname")).getText().equals(contactData.firstName)) {
            type(By.name("firstname"), contactData.firstName);
        }
        System.out.println("firstName: " + contactData.firstName);

        if (!wd.findElement(By.name("lastname")).getText().equals(contactData.lastName)) {
            type(By.name("lastname"), contactData.lastName);
        }
        System.out.println("lastName: " + contactData.lastName);

        if (!wd.findElement(By.name("nickname")).getText().equals(contactData.nickName)) {
            type(By.name("nickname"), contactData.nickName);
        }
        System.out.println("nickName: " + contactData.nickName);

        if (!wd.findElement(By.name("company")).getText().equals(contactData.companyName)) {
            type(By.name("company"), contactData.companyName);
        }
        System.out.println("companyName: " + contactData.companyName);

        if (!wd.findElement(By.name("home")).getText().equals(contactData.homePhoneNumber)) {
            type(By.name("home"), contactData.homePhoneNumber);
        }
        System.out.println("homePhoneNumber: " + contactData.homePhoneNumber);

        if (isElementPresent(By.name("new_group"))) {
            String group = contactData.getGroup();
            System.out.println("group: " + contactData.group);

            if (wd.findElement(By.name("new_group")).getText().equals(group)) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(group);
            }
            //else { new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1"); }
        }

        contactBirthday(contactData);
    }

    private void contactBirthday(ContactData contactData) {
        byte i;
        if (isElementPresent(By.name("update"))) {
            i = 2;
        } else i = 1;

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.birthdayDay + 2) + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]"));
            click(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.birthdayDay + 2) + "]"));
        }
        System.out.println("birthdayDay: " + contactData.birthdayDay);

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.birthdayMonth + i) + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]"));
            click(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.birthdayMonth + i) + "]"));
        }
        System.out.println("birthdayMonth: " + contactData.birthdayMonth);

        if (!wd.findElement(By.name("byear")).getText().equals(String.valueOf(contactData.birthdayYear))) {
            type(By.name("byear"), String.valueOf(contactData.birthdayYear));
        }

        System.out.println("birthdayYear: " + contactData.birthdayYear);
    }

    public void editContact(int numContact) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + numContact + "]/td[8]/a/img"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        alert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
