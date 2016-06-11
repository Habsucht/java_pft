package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class GroupData {
    public String groupName;
    public String header;
    public String footer;

    public GroupData(String groupName, String header, String footer) {
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }

    public GroupData() {
        String group = GroupDataGenerator.generateGroup();

        this.groupName = group;
        this.header = "This" + group;
        this.footer = group;
    }
}
