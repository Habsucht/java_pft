package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends BaseTests {

    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreation();
        }

        app.getContactHelper().selectContact(2);
        app.getContactHelper().deleteContact();
        app.getBaseHelper().alert();
    }


}
