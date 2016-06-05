package ru.stqa.pft.addressbook.data;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String homePhoneNumber;
    public final String getBirthdayYear;

    public ContactData(String firstName, String lastName, String nickName, String companyName, String homePhoneNumber, String getBirthdayYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.companyName = companyName;
        this.homePhoneNumber = homePhoneNumber;
        this.getBirthdayYear = getBirthdayYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }
}
