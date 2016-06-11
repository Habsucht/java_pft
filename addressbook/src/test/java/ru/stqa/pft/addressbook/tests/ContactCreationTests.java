package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.ContactData;

import org.testng.annotations.Test;

public class ContactCreationTests extends BaseTests {

    @Test
    public static void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData());
        app.getContactHelper().submitModification();
    }
}
