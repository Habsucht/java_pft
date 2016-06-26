/**
 *  The class for the implementation of the data group
 */

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

    @Override
    public String toString() {
        return "GroupData{" +
                "groupName='" + groupName + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (groupName != null ? !groupName.equals(groupData.groupName) : groupData.groupName != null) return false;
        if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
        return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;

    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        return result;
    }
}
