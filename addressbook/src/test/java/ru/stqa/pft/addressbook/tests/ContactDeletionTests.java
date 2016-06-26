/**
 *  A class to test the contact removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

import java.util.List;

import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class ContactDeletionTests extends BaseTests {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }

        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        int index = generateRandom(beforeContactList.size());

        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteContact();

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Check on the number of elements
        Assert.assertEquals(afterContactList.size(), beforeContactList.size() - 1);

        // Check elements for identity verification
        beforeContactList.remove(index);
        Assert.assertEquals(beforeContactList, afterContactList);
    }
}
