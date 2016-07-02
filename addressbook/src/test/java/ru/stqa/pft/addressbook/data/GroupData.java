/**
 *  The class for the implementation of the data group
 */

package ru.stqa.pft.addressbook.data;

import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

public class GroupData {
    private int groupId;

    private String groupName;
    private String header;
    private String footer;

    public GroupData() {
        String group = GroupDataGenerator.generateGroup();

        this.groupId = 0;
        this.groupName = group;
        this.header = "This" + group;
        this.footer = group;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupHeader(String header) {
        this.header = header;
    }

    public void setGroupFooter(String footer) {
        this.footer = footer;
    }

    public int getGroupId() {
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

        if (groupId != groupData.groupId) return false;
        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;

    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
