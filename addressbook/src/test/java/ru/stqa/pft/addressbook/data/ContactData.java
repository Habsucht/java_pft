/**
 *  The class for the implementation of the contact data
 */

package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.ContactDataGenerator;
import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class ContactData {
    private int contactId;

    private String firstName;
    private String lastName;
    private String nickName;
    private String companyName;

    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String workPhoneNumber;

    private String group;

    private int birthdayDay;
    private String birthdayMonth;
    private int birthdayYear;

    public ContactData(int contactId) {
        this.contactId = contactId;
    }

    public ContactData() {
        this.contactId = 0;

        this.firstName = ContactDataGenerator.generateFirstName();
        this.lastName = ContactDataGenerator.generateLastName();
        this.nickName = ContactDataGenerator.generateNickName(this.firstName);

        this.companyName = ContactDataGenerator.generateCompanyName();

        this.homePhoneNumber = ContactDataGenerator.generatePhoneNumber();
        this.mobilePhoneNumber = ContactDataGenerator.generatePhoneNumber();
        this.workPhoneNumber = ContactDataGenerator.generatePhoneNumber();

        this.group = GroupDataGenerator.generateGroup();

        this.birthdayDay = ContactDataGenerator.generateDay();
        this.birthdayMonth = ContactDataGenerator.generateMonth();
        this.birthdayYear = ContactDataGenerator.generateYear();
    }

    public ContactData setContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }

    public ContactData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ContactData setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public ContactData setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public ContactData setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public ContactData setGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
        return this;
    }

    public ContactData setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
        return this;
    }

    public ContactData setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
        return this;
    }

    public int getContactId() {
        return this.contactId;
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

    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return this.workPhoneNumber;
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
