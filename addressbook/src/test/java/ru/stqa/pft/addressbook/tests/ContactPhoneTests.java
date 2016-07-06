package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

import static ru.stqa.pft.addressbook.tests.BaseTests.app;

public class ContactPhoneTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreationVer1();
        }
    }

    @Test
    // Base test phone contacts
    public static void testContactPhone() {
        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

    }
}
