package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactPhoneTests extends BaseTests{
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        // Checking for the presence of at least one contact with the subsequent creation
        if (!app.getContactHelper().isThereAContact()) {
            ContactCreationTests.testContactCreationVer1();
        }
    }

    @Test
    // Base test phone contacts
    public static void testContactPhone() {
        // Selection of accidental contact
        ContactData contact = app.getContactHelper().all().iterator().next();

        // Data collection from the contact
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllPhoneNumber(), equalTo(mergePhone(contactInfoFromEditForm)));
    }

    public static String mergePhone(ContactData contact) {
        /*
        String result = "";
        if (contact.getHomePhoneNumber() != null) { result = result + contact.getHomePhoneNumber(); }
        if (contact.getMobilePhoneNumber() != null) { result = result + "\n" + contact.getMobilePhoneNumber(); }
        if (contact.getWorkPhoneNumber() != null) { result = result + "\n" + contact.getWorkPhoneNumber(); }
        return result;
        */
        return Arrays.asList(contact.getHomePhoneNumber(), contact.getMobilePhoneNumber(), contact.getWorkPhoneNumber())
                .stream().filter((s) -> !s.equals(""))      // Filter the flow of blank lines
                .map(ContactPhoneTests::cleanedPhone)       // The application features to the stream
                .collect(Collectors.joining("\n"));         // Merge lines delimited \n
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-+()]", "");
    }
}
