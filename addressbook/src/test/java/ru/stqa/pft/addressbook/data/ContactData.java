package ru.stqa.pft.addressbook.data;

public class ContactData {
    public String firstName;
    public String lastName;
    public String nickName;
    public String companyName;
    public String homePhoneNumber;

    public int birthdayDay;
    public int birthdayMonth;
    public int birthdayYear;

    public ContactData(String firstName, String lastName, String nickName, String companyName, String homePhoneNumber, int birthdayDay, int birthdayMonth, int birthdayYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.companyName = companyName;
        this.homePhoneNumber = homePhoneNumber;

        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;

    }

}
