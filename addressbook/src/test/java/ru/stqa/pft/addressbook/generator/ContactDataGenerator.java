package ru.stqa.pft.addressbook.generator;

public class ContactDataGenerator extends BaseGenerator {
    private static String[] firstName  = {"Liam", "Noah", "Mason", "Ethan", "Logan", "Lucas", "Jackson", "Aiden", "Oliver", "Jacob", "Elijah", "Alexander", "James", "Benjamin", "Jack",
            "Luke", "William", "Michael", "Owen", "Daniel", "Carter", "Gabriel", "Henry", "Matthew", "Wyatt", "Caleb", "Jayden", "Nathan", "Ryan", "Isaac"};

    private static String[] lastName = {"Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Wood", "Lewis", "Scott", "Cooper", "King", "Green", "Walker", "Edwards", "Turner", "Morgan", "Baker", "Hill", "Phillips"};

    private static String[] companyName = {"Facebook", "Yandex", "VK", "Google", " ", "", " ", " ", " ", " "};

    public static String generateFirstName() {
        return firstName[generateRandom(30)];
    }

    public static String generateLastName() {
        return lastName[generateRandom(30)];
    }

    public static String generateNickName(String firstName) {
        return firstName + generateRandom(100);
    }

    public static String generateHomePhoneNumber() {
        return "8(9" + generateRandom(10) + generateRandom(10) + ")" + generateRandom(10) + generateRandom(10) + generateRandom(10) + "-" + generateRandom(10) + generateRandom(10) + "-" + generateRandom(10) + generateRandom(10);
    }

    public static String generateCompanyName() {
        return companyName[generateRandom(10)];
    }

    public static int generateDay() {
        return generateRandom(31);
    }

    public static int generateMonth() {
        return generateRandom(12);
    }

    public static int generateYear() {
        return generateRandom(1950 + generateRandom(50));
    }



}
