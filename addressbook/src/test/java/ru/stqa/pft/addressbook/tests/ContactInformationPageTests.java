package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.generator.ContactDataGenerator;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

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
    public static void testContactInformationPageVer1() {
        // Selection of accidental contact
        ContactData contact = app.getContactHelper().all().iterator().next();

        // Data collection from the contact
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        String allInfoFromInfoPage = app.getContactHelper().infoFromInfoPage(contact);
        String allInfoFromEditForm = mergeInfo(contactInfoFromEditForm);

        System.out.println(allInfoFromInfoPage);
        System.out.println(allInfoFromEditForm);
    }

    public static String mergeInfo(ContactData contact) {
        String firstParagraph = contact.getFirstName() + " " + contact.getLastName() + "\n"
                + Arrays.asList(contact.getNickName(), contact.getCompanyName(), contact.getPostAddress())
                .stream().filter((s) -> !s.equals(""))      // Filter the flow of blank lines
                .collect(Collectors.joining("\n"));         // Merge lines delimited \n

        String secondParagraph = "";
        if (contact.getHomePhoneNumber() != null) { secondParagraph = secondParagraph + "H: " + contact.getHomePhoneNumber(); }
        if (contact.getMobilePhoneNumber() != null) { secondParagraph = secondParagraph + "\n" + "M: " + contact.getMobilePhoneNumber(); }
        if (contact.getWorkPhoneNumber() != null) { secondParagraph = secondParagraph + "\n" + "W: " + contact.getWorkPhoneNumber(); }
        if (contact.getFaxPhoneNumber() != null) { secondParagraph = secondParagraph + "\n" + "F: " + contact.getFaxPhoneNumber(); }

        String thirdParagraph = "";
        if (contact.getEmailAddress1() != null) { thirdParagraph = thirdParagraph + contact.getEmailAddress1()
                + " (www." + contact.getEmailAddress1().substring(contact.getEmailAddress1().indexOf("@") + 1) + ")"; }
        if (contact.getEmailAddress2() != null) { thirdParagraph = thirdParagraph + "\n" + contact.getEmailAddress2()
                + " (www." + contact.getEmailAddress2().substring(contact.getEmailAddress2().indexOf("@") + 1) + ")"; }
        if (contact.getEmailAddress3() != null) { thirdParagraph = thirdParagraph + "\n" + contact.getEmailAddress3()
                + " (www." + contact.getEmailAddress3().substring(contact.getEmailAddress3().indexOf("@") + 1) + ")"; }

        String fourthParagraph = "";
        if (contact.getBirthdayDay() != null | contact.getBirthdayMonth() != null | contact.getBirthdayYear() != null) { fourthParagraph = fourthParagraph + "Birthday "; }
        if (contact.getBirthdayDay() != null) { fourthParagraph = fourthParagraph + contact.getBirthdayDay() + ". "; }
        if (contact.getBirthdayMonth() != null) { fourthParagraph = fourthParagraph + contact.getBirthdayMonth() + " "; }
        if (contact.getBirthdayYear() != null) { fourthParagraph = fourthParagraph + contact.getBirthdayYear() + " (" + getAge(contact) + ")"; }

        return firstParagraph + "\n\n" + secondParagraph + "\n\n" + thirdParagraph + "\n\n" + fourthParagraph;
    }

    private static int getAge(ContactData contact) {
        int month = 0;
        for (int i = 0; i < ContactDataGenerator.getBirthdayMonth().length; i++) {
            if (contact.getBirthdayMonth().equals(ContactDataGenerator.getBirthdayMonth()[i])) month = i + 1;  }

        Date date = new Date();
        System.out.println(date.getYear());

        int age = (date.getYear() - Integer.parseInt(contact.getBirthdayYear()));

        if (date.getDay()*date.getMonth() - Integer.parseInt(contact.getBirthdayDay())*month >= 0) { age = age + 1; }

        return age;
    }
}
