/**
 *  A class to test the change of contact data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class ContactModificationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreationVer1();
        }
    }

    @Test
    // Base test modification contact
    public void testContactModificationVer1() {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        int index = generateRandom(beforeContactList.size());
        ContactData contact = new ContactData();

        app.getContactHelper().editContactByIndex(index);
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        // Check on the number of elements
        Assert.assertEquals(app.getContactHelper().getContactCount(), beforeContactList.size());

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Assign Id the modification element
        contact.setContactId(beforeContactList.get(index).getContactId());
        beforeContactList.remove(index);
        beforeContactList.add(contact);

        // Sort the list by id
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getContactId(), c2.getContactId());
        beforeContactList.sort(byId);
        afterContactList.sort(byId);

        // Check elements for identity verification
        Assert.assertEquals(new HashSet<Object>(beforeContactList), new HashSet<Object>(afterContactList));
    }

    @Test
    // Test modification contact fluent implementation
    public void testContactModificationVer2() {
        Contacts beforeContactSet = app.getContactHelper().all();

        ContactData modifiedContact = beforeContactSet.iterator().next();
        ContactData contact = new ContactData();

        app.getContactHelper().editContactById(modifiedContact.getContactId());
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        // Check on the number of elements
        assertThat(app.getContactHelper().getContactCount(), equalTo(beforeContactSet.size()));

        Contacts afterContactSet = app.getContactHelper().all();

        // Assign Id the modification element
        contact.setContactId(modifiedContact.getContactId());

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withOut(modifiedContact).withAdded(contact)));
    }
}
