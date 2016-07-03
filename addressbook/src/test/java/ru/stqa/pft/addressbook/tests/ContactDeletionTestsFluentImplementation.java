/**
 *  A class to test the contact removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class ContactDeletionTestsFluentImplementation extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts beforeContactSet = app.getContactHelper().all();

        ContactData deletedContact = beforeContactSet.iterator().next();

        app.getContactHelper().selectContactById(deletedContact.getContactId());
        app.getContactHelper().deleteContact();

        Contacts afterContactSet = app.getContactHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterContactSet.size(), beforeContactSet.size() - 1);

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withOut(deletedContact)));
    }
}
