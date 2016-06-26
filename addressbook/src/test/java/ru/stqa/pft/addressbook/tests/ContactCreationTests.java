/**
 *  A class to test the creation of a new contact
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.data.ContactData;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends BaseTests {

    @Test
    public static void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        ContactData contact = new ContactData();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Check on the number of elements
        Assert.assertEquals(afterContactList.size(), beforeContactList.size() + 1);

        // Check elements for identity verification
        for (ContactData c : afterContactList) {
            if (c.getContactId() > contact.getContactId()) {
                contact.setContactId(c.getContactId());
            }
        }
        beforeContactList.add(contact);
        Assert.assertEquals(new HashSet<Object>(beforeContactList), new HashSet<Object>(afterContactList));
    }
}
