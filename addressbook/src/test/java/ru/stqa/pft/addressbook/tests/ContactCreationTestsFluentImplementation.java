/**
 *  A class to test the creation of a new contact
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactCreationTestsFluentImplementation extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public static void testContactCreation() {
        Contacts beforeContactSet = app.getContactHelper().all();

        ContactData contact = new ContactData();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        Contacts afterContactSet = app.getContactHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterContactSet.size(), beforeContactSet.size() + 1);

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withAdded(contact.setContactId(afterContactSet.stream().mapToInt((g) -> g.getContactId()).max().getAsInt()))));
    }
}
