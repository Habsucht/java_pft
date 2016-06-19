/**
 *  The class for the implementation of the contact data
 */

package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.ContactDataGenerator;
import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class ContactData {
    public String firstName;
    public String lastName;
    public String nickName;
    public String companyName;
    public String homePhoneNumber;

    public String group;

    public int birthdayDay;
    public String birthdayMonth;
    public int birthdayYear;

    public ContactData(String firstName, String lastName, String nickName, String companyName, String homePhoneNumber, int birthdayDay, String birthdayMonth,
                       int birthdayYear, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;

        this.homePhoneNumber = homePhoneNumber;

        this.companyName = companyName;

        this.group = group;

        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
    }

    public ContactData() {
        this.firstName = ContactDataGenerator.generateFirstName();
        this.lastName = ContactDataGenerator.generateLastName();
        this.nickName = ContactDataGenerator.generateNickName(this.firstName);

        this.homePhoneNumber = ContactDataGenerator.generateHomePhoneNumber();

        this.companyName = ContactDataGenerator.generateCompanyName();

        this.group = GroupDataGenerator.generateGroup();

        this.birthdayDay = ContactDataGenerator.generateDay();
        this.birthdayMonth = ContactDataGenerator.generateMonth();
        this.birthdayYear = ContactDataGenerator.generateYear();
    }

    public String getGroup() {
        return this.group;
    }
}
