/**
 *  A class to test the change of contact data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class ContactModificationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        int index = generateRandom(beforeContactList.size());
        ContactData contact = new ContactData();

        app.getContactHelper().editContact(index);
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Check on the number of elements
        Assert.assertEquals(afterContactList.size(), beforeContactList.size());

        // Check elements for identity verification
        contact.setContactId(beforeContactList.get(index).getContactId());
        beforeContactList.remove(index);
        beforeContactList.add(contact);

        // Sort the list by id
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getContactId(), c2.getContactId());
        beforeContactList.sort(byId);
        afterContactList.sort(byId);

        Assert.assertEquals(new HashSet<Object>(beforeContactList), new HashSet<Object>(afterContactList));
    }
}
