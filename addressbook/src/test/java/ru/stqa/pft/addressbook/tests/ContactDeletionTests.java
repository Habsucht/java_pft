/**
 *  A class to test the contact removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends BaseTests {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }

        int beforeCount = app.getContactHelper().getContactCount();

        app.getContactHelper().selectContact(2);
        app.getContactHelper().deleteContact();

        app.getNavigationHelper().returnToHomePage();

        int afterCount = app.getContactHelper().getContactCount();
        Assert.assertEquals(afterCount, beforeCount - 1);
    }
}
