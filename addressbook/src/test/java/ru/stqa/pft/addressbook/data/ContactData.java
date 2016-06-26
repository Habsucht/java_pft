/**
 *  The class for the implementation of the contact data
 */

package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.ContactDataGenerator;
import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class ContactData {
    private int contactId;

    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String homePhoneNumber;

    private final String group;

    private final int birthdayDay;
    private final String birthdayMonth;
    private final int birthdayYear;

    public ContactData(int contactId, String firstName, String lastName, String nickName, String companyName, String homePhoneNumber, String group, int birthdayDay, String birthdayMonth,
                       int birthdayYear) {
        this.contactId = contactId;

        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;

        this.companyName = companyName;

        this.homePhoneNumber = homePhoneNumber;

        this.group = group;

        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
    }

    public ContactData() {
        this.contactId = 0;

        this.firstName = ContactDataGenerator.generateFirstName();
        this.lastName = ContactDataGenerator.generateLastName();
        this.nickName = ContactDataGenerator.generateNickName(this.firstName);

        this.companyName = ContactDataGenerator.generateCompanyName();

        this.homePhoneNumber = ContactDataGenerator.generateHomePhoneNumber();

        this.group = GroupDataGenerator.generateGroup();

        this.birthdayDay = ContactDataGenerator.generateDay();
        this.birthdayMonth = ContactDataGenerator.generateMonth();
        this.birthdayYear = ContactDataGenerator.generateYear();
    }

    public int getContactId() {
        return this.contactId;
    }

    public void setContactId(int contactId) {
         this.contactId = contactId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getHomePhoneNumber() {
        return this.homePhoneNumber;
    }

    public int getBirthdayDay() {
        return this.birthdayDay;
    }

    public String getBirthdayMonth() {
        return this.birthdayMonth;
    }

    public int getBirthdayYear() {
        return this.birthdayYear;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (contactId != that.contactId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
