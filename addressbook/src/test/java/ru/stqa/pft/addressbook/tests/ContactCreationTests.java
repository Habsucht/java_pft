package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.ContactData;

import org.testng.annotations.Test;

public class ContactCreationTests extends BaseTests {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Jon", "Dou", "void", "bigbox", "855614452266", "1876"));
        app.getContactHelper().submitContactCreation();
    }
}
