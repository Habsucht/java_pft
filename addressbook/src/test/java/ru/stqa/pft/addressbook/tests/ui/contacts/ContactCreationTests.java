/**
 *  A class to test the creation of a new contact
 */

package ru.stqa.pft.addressbook.tests.ui.contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.generator.BaseGenerator;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.ui.BaseTests;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactCreationTests extends BaseTests {
    @DataProvider
    public Iterator<Object[]> generateValidContacts() {
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < BaseGenerator.generateRandom(5); i++) {
            list.add(new Object[]{new ContactData()});
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loadValidContactsXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            List<ContactData> groups = (List<ContactData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
            }
    }

    @DataProvider
    public Iterator<Object[]> loadValidContactsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();
    }

    @Test(dataProvider = "generateValidContacts")
    // Base test creation contact
    public static void testContactCreationVer1(ContactData contact) {
        List<ContactData> beforeContactList = app.getContactHelper().getContactList();

        contact.setPhoto(new File("src/test/resources/image1.jpg"));

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        // Check on the number of elements
        Assert.assertEquals(app.getContactHelper().getContactCount(), beforeContactList.size() + 1);

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

    @Test(dataProvider = "loadValidContactsXml")
    // Test creation contact fluent implementation
    public static void testContactCreationVer2(ContactData contact) {
        Contacts beforeContactSet = app.getContactHelper().all();

        contact.setPhoto(new File("src/test/resources/image2.png"));

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();

        app.getNavigationHelper().returnToHomePage();

        // Check on the number of elements
        assertThat(app.getContactHelper().getContactCount(), equalTo(beforeContactSet.size() + 1));

        Contacts afterContactSet = app.getContactHelper().all();

        // Check elements for identity verification
        assertThat(afterContactSet, equalTo(beforeContactSet.withAdded(contact.setContactId(afterContactSet.stream().mapToInt((g) -> g.getContactId()).max().getAsInt()))));
    }
}
