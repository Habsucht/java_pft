/**
 *  Class data generation for groups
 */

package ru.stqa.pft.addressbook.generator;

public class GroupDataGenerator extends BaseGenerator {
    private static String[] group = {"Home", "Work", "Other", "Test1", "Test2"};

    public static String generateGroup() {
        return group[generateRandom(group.length)];
    }
}
