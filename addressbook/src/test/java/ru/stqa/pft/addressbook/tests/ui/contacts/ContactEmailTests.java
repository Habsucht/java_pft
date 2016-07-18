/**
 *  A class to test the valid email address of contact data
 */
package ru.stqa.pft.addressbook.tests.ui.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.tests.ui.BaseTests;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactEmailTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        // Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreationVer1(new ContactData());
        }
    }

    @Test
    // Base test email contacts
    public static void testContactEmail() {
        // Selection of accidental contact
        ContactData contact = app.getContactHelper().all().iterator().next();

        // Data collection from the contact
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllEmailAddress(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private static String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmailAddress1(), contact.getEmailAddress2(), contact.getEmailAddress3())
                .stream().filter((s) -> !s.equals(""))      // Filter the flow of blank lines
                .collect(Collectors.joining("\n"));         // Merge lines delimited \n
    }
}
