/**
 *  The class implements the action with contacts
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.data.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + numContact + "]/td[1]/input"));
    }

    public void fillContactForm(ContactData contactData) {
        if (!wd.findElement(By.name("firstname")).getText().equals(contactData.getFirstName())) {
            type(By.name("firstname"), contactData.getFirstName());
        }
        System.out.println("firstName: " + contactData.getFirstName());

        if (!wd.findElement(By.name("lastname")).getText().equals(contactData.getLastName())) {
            type(By.name("lastname"), contactData.getLastName());
        }
        System.out.println("lastName: " + contactData.getLastName());

        if (!wd.findElement(By.name("nickname")).getText().equals(contactData.getNickName())) {
            type(By.name("nickname"), contactData.getNickName());
        }
        System.out.println("nickName: " + contactData.getNickName());

        if (!wd.findElement(By.name("company")).getText().equals(contactData.getCompanyName())) {
            type(By.name("company"), contactData.getCompanyName());
        }
        System.out.println("companyName: " + contactData.getCompanyName());

        if (!wd.findElement(By.name("home")).getText().equals(contactData.getHomePhoneNumber())) {
            type(By.name("home"), contactData.getHomePhoneNumber());
        }
        System.out.println("homePhoneNumber: " + contactData.getHomePhoneNumber());

        if (isElementPresent(By.name("new_group"))) {
            String group = contactData.getGroup();
            System.out.println("group: " + contactData.getGroup());

            if (wd.findElement(By.name("new_group")).getText().equals(group)) {
                select(By.name("new_group")).selectByValue(group);
            }
            //else { new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1"); }
        }

        contactBirthday(contactData);
    }

    private void contactBirthday(ContactData contactData) {
        /*
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.birthdayDay + 2) + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]"));
            click(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.birthdayDay + 2) + "]"));
        }
        */

        select(By.xpath("//div[@id='content']/form/select[1]")).selectByIndex(contactData.getBirthdayDay() + 1);
        System.out.println("birthdayDay: " + contactData.getBirthdayDay());

        /*
        byte i;
        if (isElementPresent(By.name("update"))) {
            i = 2;
        } else i = 1;
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.birthdayMonth + i) + "]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]"));
            click(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.birthdayMonth + i) + "]"));
        }
        */

        select(By.xpath("//div[@id='content']/form/select[2]")).selectByValue(contactData.getBirthdayMonth());
        System.out.println("birthdayMonth: " + contactData.getBirthdayMonth());

        if (!wd.findElement(By.name("byear")).getText().equals(String.valueOf(contactData.getBirthdayYear()))) {
            type(By.name("byear"), String.valueOf(contactData.getBirthdayYear()));
        }
        System.out.println("birthdayYear: " + contactData.getBirthdayYear());
    }

    public void editContact(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + 2 + "]/td[8]/a/img"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        alert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contactList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            int contactId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String homePhoneNumber = element.findElements(By.tagName("td")).get(5).getText();
            contactList.add(new ContactData(contactId, firstName, lastName, null, null, homePhoneNumber, null, 0, null, 0));
        }
        return contactList;
    }
}
