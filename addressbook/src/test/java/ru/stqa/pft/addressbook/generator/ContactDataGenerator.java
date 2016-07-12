/**
 *  Class data generation for contacts
 */

package ru.stqa.pft.addressbook.generator;

public class ContactDataGenerator extends BaseGenerator {
    private static String[] firstName  = {"Liam", "Noah", "Mason", "Ethan", "Logan", "Lucas", "Jackson", "Aiden", "Oliver",
            "Jacob", "Elijah", "Alexander", "James", "Benjamin", "Jack", "Luke", "William", "Michael", "Owen", "Daniel", "Carter",
            "Gabriel", "Henry", "Matthew", "Wyatt", "Caleb", "Jayden", "Nathan", "Ryan", "Isaac"};

    private static String[] lastName = {"Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Wood", "Lewis", "Scott", "Cooper", "King",
            "Green", "Walker", "Edwards", "Turner", "Morgan", "Baker", "Hill", "Phillips"};

    private static String[] companyName = {"Facebook", "Yandex", "VK", "Google", "Rambler", "Sun", "Oracle", "Apple", "Cisco", "DLink"};

    private static String[] city = {"Moscow", "NewYork", "SanFrancisco", "Brooklyn", "Paris"};

    private static String[] street = {"prosp. Stroiteley", "ul. Cvetochnaya", "3th Microraion", "Highhill Street", "Woodpark Drive"};

    private static String[] domain = {".ru", ".com", ".pw", ".net", ".free", ".int"};

    private static String[] birthdayMonth = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    public static String[] getBirthdayMonth() {
        return birthdayMonth;
    }

    public static void setBirthdayMonth(String[] birthdayMonth) {
        ContactDataGenerator.birthdayMonth = birthdayMonth;
    }

    public static String generateFirstName() {
        return firstName[generateRandom(firstName.length)];
    }

    public static String generateLastName() {
        return lastName[generateRandom(lastName.length)];
    }

    public static String generateNickName(String firstName) {
        return firstName + generateRandom(100);
    }

    public static String generateCompanyName() {
        return companyName[generateRandom(companyName.length)];
    }

    public static String generateContactAddress() {
        return city[generateRandom(city.length)] + ", " + street[generateRandom(street.length)] + " " + generateRandom(100);
    }

    public static String generatePhoneNumber() {
        return "8(9" + generateRandom(10) + generateRandom(10) + ")"
                + generateRandom(10) + generateRandom(10) + generateRandom(10) + "-"
                + generateRandom(10) + generateRandom(10) + "-"
                + generateRandom(10) + generateRandom(10);
    }

    public static String generateEmailAddress(String firstName, String lastName, String companyName) {
        return firstName + "." + lastName + "@" + companyName + domain[generateRandom(domain.length)];
    }

    public static String generateDay() {
        return String.valueOf(generateRandom(30) + 1);
    }

    public static String generateMonth() {
        return birthdayMonth[generateRandom(birthdayMonth.length)];
    }

    public static String generateYear() {
        return String.valueOf(generateRandom(50) + 1950);
    }
}
