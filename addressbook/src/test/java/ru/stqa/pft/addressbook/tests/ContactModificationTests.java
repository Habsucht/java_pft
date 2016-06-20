/**
 *  A class to test the change of contact data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

public class ContactModificationTests extends BaseTests {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }

        int beforeCount = app.getContactHelper().getContactCount();

        app.getContactHelper().editContact(2);
        app.getContactHelper().fillContactForm(new ContactData("Jon_", "Dou_", "void_", "bigbox_", "855614452266", 21, "April", 1987, null));
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        int afterCount = app.getContactHelper().getContactCount();
        Assert.assertEquals(afterCount, beforeCount);
    }
}
