package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class GroupData {
    private final String groupName;
    private final String header;
    private final String footer;

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

    public String getGroupName() {
        return groupName;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}
