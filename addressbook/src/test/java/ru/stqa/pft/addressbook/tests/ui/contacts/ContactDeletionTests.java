/**
 *  A class to test the contact removal
 */

package ru.stqa.pft.addressbook.tests.ui.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.ui.BaseTests;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class ContactDeletionTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking the presence of base data at least one contact
        if (app.getDbHelper().contacts().size() == 0) {
            ContactCreationTests.testContactCreationVer1(new ContactData());
        }

        //  Checking on the page there is at least one contact on the selector
        if (!app.getContactHelper().isThereAContact()) {
            System.out.println("On the page is not found contact");
        }
    }

    @Test
    // Base test deletion contact
    public void testContactDeletionVer1() {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        int index = generateRandom(beforeContactList.size());

        app.getContactHelper().selectContactByIndex(index);
        app.getContactHelper().deleteContact();

        // Check on the number of elements
        Assert.assertEquals(app.getContactHelper().getContactCount(), beforeContactList.size() - 1);

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Removing non-existent element
        beforeContactList.remove(index);

        // Check elements for identity verification
        Assert.assertEquals(beforeContactList, afterContactList);
    }

    @Test
    // Test deletion contact fluent implementation
    public void testContactDeletionVer2() {
        Contacts beforeContactSet = app.getContactHelper().all();

        ContactData deletedContact = beforeContactSet.iterator().next();

        app.getContactHelper().selectContactById(deletedContact.getContactId());
        app.getContactHelper().deleteContact();

        // Check on the number of elements
        assertThat(app.getContactHelper().getContactCount(), equalTo(beforeContactSet.size() - 1));

        Contacts afterContactSet = app.getContactHelper().all();

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withOut(deletedContact)));
    }

    @Test
    // Deletion contact with access to the data from the database
    public void testContactDeletionVer3() {
        Contacts beforeContactSet = app.getDbHelper().contacts();

        ContactData deletedContact = beforeContactSet.iterator().next();

        app.getContactHelper().selectContactById(deletedContact.getContactId());
        app.getContactHelper().deleteContact();

        // Check on the number of elements
        assertThat(app.getContactHelper().getContactCount(), equalTo(beforeContactSet.size() - 1));

        Contacts afterContactSet = app.getDbHelper().contacts();

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withOut(deletedContact)));
    }
}
