/**
 *  The class implements the action with contacts
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

public class ContactHelper extends BaseHelper {

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactByIndex(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + numContact + "]/td[1]/input"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

        if (!wd.findElement(By.name("mobile")).getText().equals(contactData.getMobilePhoneNumber())) {
            type(By.name("mobile"), contactData.getMobilePhoneNumber());
        }
        System.out.println("mobilePhoneNumber: " + contactData.getMobilePhoneNumber());

        if (!wd.findElement(By.name("work")).getText().equals(contactData.getWorkPhoneNumber())) {
            type(By.name("work"), contactData.getWorkPhoneNumber());
        }
        System.out.println("workPhoneNumber: " + contactData.getWorkPhoneNumber());

        if (isElementPresent(By.name("new_group"))) {
            String group = contactData.getGroup();
            System.out.println("group: " + contactData.getGroup());

            if (wd.findElement(By.name("new_group")).getText().equals(group)) {
                select(By.name("new_group")).selectByValue(group);
            }
            //else { new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1"); }
        }

        // Fill contact birthday day
        select(By.xpath("//div[@id='content']/form/select[1]")).selectByIndex(contactData.getBirthdayDay() + 1);
        System.out.println("birthdayDay: " + contactData.getBirthdayDay());

        // Fill contact birthday month
        select(By.xpath("//div[@id='content']/form/select[2]")).selectByValue(contactData.getBirthdayMonth());
        System.out.println("birthdayMonth: " + contactData.getBirthdayMonth());

        // Fill contact birthday year
        if (!wd.findElement(By.name("byear")).getText().equals(String.valueOf(contactData.getBirthdayYear()))) {
            type(By.name("byear"), String.valueOf(contactData.getBirthdayYear()));
        }
        System.out.println("birthdayYear: " + contactData.getBirthdayYear());
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getContactId());

        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
        String companyName = wd.findElement(By.name("company")).getAttribute("value");

        String homePhoneNumber = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhoneNumber = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhoneNumber = wd.findElement(By.name("work")).getAttribute("value");

        //int birthdayDay = Integer.parseInt(wd.findElement(By.name("bday")).getAttribute("selected"));
        //String birthdayMonth = wd.findElement(By.name("bmonth")).getAttribute("value");
        //int birthdayYear = Integer.parseInt(wd.findElement(By.name("byear")).getAttribute("value"));

        wd.navigate().back();
        return new ContactData(contact.getContactId()).setFirstName(firstName).setLastName(lastName).setNickName(nickName).setCompanyName(companyName)
                .setHomePhoneNumber(homePhoneNumber).setMobilePhoneNumber(mobilePhoneNumber).setWorkPhoneNumber(workPhoneNumber);
                //.setBirthdayDay(birthdayDay).setBirthdayMonth(birthdayMonth).setBirthdayYear(birthdayYear);
    }

    public void editContactByIndex(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + 2 + "]/td[8]/a/img"));
    }

    public void editContactById(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
        // wd.get("http://localhost/addressbook/edit.php?id=" + id);
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
        AllContacts(contactList);
        return contactList;
    }

    public Set<ContactData> getContactSet() {
        Set<ContactData> contactSet = new HashSet<>();
        AllContacts(contactSet);
        return contactSet;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        AllContacts(contacts);
        return contacts;
    }

    private void AllContacts(Collection<ContactData> collection) {
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            int contactId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String[] phones = element.findElements(By.tagName("td")).get(5).getText().split("\n");
            collection.add(new ContactData(contactId).setFirstName(firstName).setLastName(lastName)
                    .setHomePhoneNumber(phones[0]).setMobilePhoneNumber(phones[1]).setWorkPhoneNumber(phones[2]));
        }
    }
}
