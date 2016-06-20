/**
 *  A class to test the creation of a new contact
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.data.ContactData;

import org.testng.annotations.Test;

public class ContactCreationTests extends BaseTests {

    @Test
    public static void testContactCreation() {
        app.getNavigationHelper().gotoGroupPage();

        int beforeCount = app.getContactHelper().getContactCount();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData());
        app.getContactHelper().submitModification();

        int afterCount = app.getContactHelper().getContactCount();
        Assert.assertEquals(afterCount, beforeCount + 1);
    }
}
