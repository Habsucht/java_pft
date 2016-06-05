package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

public class ContactModificationTests extends BaseTests {

    @Test
    public void testContactModification() {
        app.getContactHelper().editContact(2);
        app.getContactHelper().fillContactForm(new ContactData("Jon_", "Dou_", "void_", "bigbox_", "855614452266", 10, 1, 1987));
        app.getContactHelper().updateContact();
    }

}
