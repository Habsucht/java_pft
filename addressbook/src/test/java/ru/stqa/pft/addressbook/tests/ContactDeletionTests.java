/**
 *  A class to test the contact removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends BaseTests {

    @Test
    public void testContactDeletion() {
        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }

        app.getContactHelper().selectContact(2);
        app.getContactHelper().deleteContact();
    }
}
