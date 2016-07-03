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

public class ContactModificationTestsFluentImplementation extends BaseTests {
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
        Contacts beforeContactSet = app.getContactHelper().all();

        ContactData modifiedContact = beforeContactSet.iterator().next();
        ContactData contact = new ContactData();

        app.getContactHelper().editContactById(modifiedContact.getContactId());
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        Contacts afterContactSet = app.getContactHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterContactSet.size(), beforeContactSet.size());

        // Assign Id the modification element
        contact.setContactId(modifiedContact.getContactId());

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withOut(modifiedContact).withAdded(contact)));
    }
}
