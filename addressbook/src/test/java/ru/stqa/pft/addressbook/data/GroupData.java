/**
 *  The class for the implementation of the data group
 */

package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class GroupData {
    private final String groupId;
    private final String groupName;
    private final String header;
    private final String footer;

    public GroupData(String groupId, String groupName, String header, String footer) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(String groupId) {
        String group = GroupDataGenerator.generateGroup();

        this.groupId = groupId;
        this.groupName = group;
        this.header = "This" + group;
        this.footer = group;
    }

    public String getGroupId() {
        return groupId;
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

    @Override
    public String toString() {
        return "GroupData{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (groupId != null ? !groupId.equals(groupData.groupId) : groupData.groupId != null) return false;
        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;

    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
