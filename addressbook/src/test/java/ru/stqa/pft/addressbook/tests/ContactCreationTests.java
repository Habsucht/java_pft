/**
 *  A class to test the creation of a new contact
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public static void testContactCreation() {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        ContactData contact = new ContactData();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Check on the number of elements
        Assert.assertEquals(afterContactList.size(), beforeContactList.size() + 1);

        // Check elements for identity verification
        for (ContactData c : afterContactList) {
            if (c.getContactId() > contact.getContactId()) {
                contact.setContactId(c.getContactId());
            }
        }
        /* implementation without the for loop
        contact.setContactId(afterContactList.stream().max((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId())).get().getContactId());
         */
        beforeContactList.add(contact);

        // Sort the list by id
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getContactId(), c2.getContactId());
        beforeContactList.sort(byId);
        afterContactList.sort(byId);

        Assert.assertEquals(new HashSet<Object>(beforeContactList), new HashSet<Object>(afterContactList));
    }
}
