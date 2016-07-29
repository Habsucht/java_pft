/**
 *  Class data generation for contacts
 */

package ru.stqa.pft.mantis.generator;

public class UserDataGenerator extends BaseGenerator {
    private static String[] firstName  = {"Liam", "Noah", "Mason", "Ethan", "Logan", "Lucas", "Jackson", "Aiden", "Oliver",
            "Jacob", "Elijah", "Alexander", "James", "Benjamin", "Jack", "Luke", "William", "Michael", "Owen", "Daniel", "Carter",
            "Gabriel", "Henry", "Matthew", "Wyatt", "Caleb", "Jayden", "Nathan", "Ryan", "Isaac"};


    public static String generateFirstName() {
        return firstName[generateRandom(firstName.length)];
    }

    public static String generateNickName(String firstName) {
        return firstName + generateRandom(100);
    }

    public static String generatePassword(String firstName) {
        return firstName + "Password";
    }

    public static String generateEmailAddress(String nickName) {
        return nickName + "@localhost";
    }
}
