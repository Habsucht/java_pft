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

public class ContactCreationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    // Base test creation contact
    public static void testContactCreationVer1() {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        ContactData contact = new ContactData();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        List<ContactData> afterContactList = app.getContactHelper().getContactList();

        // Check on the number of elements
        Assert.assertEquals(afterContactList.size(), beforeContactList.size() + 1);

        // Find the maximum Id and assign the created element
        for (ContactData c : afterContactList) {
            if (c.getContactId() > contact.getContactId()) {
                contact.setContactId(c.getContactId());
            }
        }
        /* implementation without the for loop
        contact.setContactId(afterContactList.stream().max((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId())).get().getContactId());
         */
        beforeContactList.add(contact);

        /* Sort the list by id
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getContactId(), c2.getContactId());
        beforeContactList.sort(byId);
        afterContactList.sort(byId);
        */

        // Check elements for identity verification
        Assert.assertEquals(new HashSet<Object>(beforeContactList), new HashSet<Object>(afterContactList));
    }

    @Test
    // Test creation contact fluent implementation
    public static void testContactCreationVer2() {
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
