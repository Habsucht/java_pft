/**
 *  The class for the implementation of the contact data
 */

package ru.stqa.pft.addressbook.data;

import org.hibernate.annotations.Type;
import ru.stqa.pft.addressbook.generator.ContactDataGenerator;
import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int contactId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Column(name = "company")
    private String companyName;

    @Column(name = "address")
    @Type(type = "text")
    private String postAddress;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhoneNumber;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhoneNumber;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhoneNumber;

    @Column(name = "fax")
    @Type(type = "text")
    private String faxPhoneNumber;

    @Transient
    private String allPhoneNumber;

    @Column(name = "email")
    @Type(type = "text")
    private String emailAddress1;

    @Column(name = "email2")
    @Type(type = "text")
    private String emailAddress2;

    @Column(name = "email3")
    @Type(type = "text")
    private String emailAddress3;

    @Transient
    private String allEmailAddress;

    @Transient
    private String group;

    @Column(name = "bday")
    @Type(type = "int" )
    private int birthdayDay;

    @Transient
    //@Column(name = "bmonth")
    private String birthdayMonth;

    @Transient
    //@Column(name = "byear")
    private String birthdayYear;

    public ContactData(int contactId) {
        this.contactId = contactId;
    }


    public ContactData() {
        this.contactId = 0;

        this.firstName = ContactDataGenerator.generateFirstName();
        this.lastName = ContactDataGenerator.generateLastName();
        this.nickName = ContactDataGenerator.generateNickName(this.firstName);

        this.companyName = ContactDataGenerator.generateCompanyName();

        this.postAddress = ContactDataGenerator.generateContactAddress();

        this.homePhoneNumber = ContactDataGenerator.generatePhoneNumber();
        this.mobilePhoneNumber = ContactDataGenerator.generatePhoneNumber();
        this.workPhoneNumber = ContactDataGenerator.generatePhoneNumber();
        this.faxPhoneNumber = ContactDataGenerator.generatePhoneNumber();

        this.emailAddress1 = ContactDataGenerator.generateEmailAddress(firstName, lastName, companyName);
        this.emailAddress2 = ContactDataGenerator.generateEmailAddress(firstName, lastName, companyName);
        this.emailAddress3 = ContactDataGenerator.generateEmailAddress(firstName, lastName, companyName);

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

    public ContactData setPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ContactData setPostAddress(String postAddress) {
        this.postAddress = postAddress;
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

    public ContactData setFaxPhoneNumber(String faxPhoneNumber) {
        this.faxPhoneNumber = faxPhoneNumber;
        return this;
    }

    public ContactData setAllPhoneNumber(String allPhoneNumber) {
        this.allPhoneNumber = allPhoneNumber;
        return this;
    }

    public ContactData setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
        return this;
    }

    public ContactData setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
        return this;
    }

    public ContactData setEmailAddress3(String emailAddress3) {
        this.emailAddress3 = emailAddress3;
        return this;
    }

    public ContactData setAllEmailAddress(String allEmailAddress) {
        this.allEmailAddress = allEmailAddress;
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

    public ContactData setBirthdayYear(String birthdayYear) {
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

    public File getPhoto() {
        return new File(photo);
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getPostAddress() {
        return postAddress;
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

    public String getFaxPhoneNumber() {
        return faxPhoneNumber;
    }

    public String getAllPhoneNumber() {
        return this.allPhoneNumber;
    }

    public String getEmailAddress1() {
        return emailAddress1;
    }

    public String getEmailAddress2() {
        return emailAddress2;
    }

    public String getEmailAddress3() {
        return emailAddress3;
    }

    public String getAllEmailAddress() {
        return allEmailAddress;
    }

    public int getBirthdayDay() {
        return this.birthdayDay;
    }

    public String getBirthdayMonth() {
        return this.birthdayMonth;
    }

    public String getBirthdayYear() {
        return this.birthdayYear;
    }

    public String getGroup() {
        return this.group;
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
    public String toString() {
        return "ContactData{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", postAddress='" + postAddress + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", workPhoneNumber='" + workPhoneNumber + '\'' +
                ", faxPhoneNumber='" + faxPhoneNumber + '\'' +
                ", allPhoneNumber='" + allPhoneNumber + '\'' +
                ", emailAddress1='" + emailAddress1 + '\'' +
                ", emailAddress2='" + emailAddress2 + '\'' +
                ", emailAddress3='" + emailAddress3 + '\'' +
                ", allEmailAddress='" + allEmailAddress + '\'' +
                ", group='" + group + '\'' +
                ", birthdayDay=" + birthdayDay +
                ", birthdayMonth='" + birthdayMonth + '\'' +
                ", birthdayYear=" + birthdayYear +
                '}';
    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
