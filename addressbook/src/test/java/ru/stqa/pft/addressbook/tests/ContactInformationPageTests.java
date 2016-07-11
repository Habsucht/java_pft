package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactInformationPageTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        // Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreationVer1();
        }
    }

    @Test
    // Test page information contacts
}
