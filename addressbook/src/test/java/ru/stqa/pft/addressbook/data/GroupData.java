/**
 *  The class for the implementation of the data group
 */

package ru.stqa.pft.addressbook.data;

import org.hibernate.annotations.Type;
import ru.stqa.pft.addressbook.generator.GroupDataGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_list")
public class GroupData {
    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_header")
    @Type(type = "text")
    private String header;

    @Column(name = "group_footer")
    @Type(type = "text")
    private String footer;

    @ManyToMany
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<ContactData> contacts = new HashSet<>();

    public GroupData() {
        String group = GroupDataGenerator.generateGroup();

        this.groupId = 0;
        this.groupName = group;
        this.header = "This" + group;
        this.footer = group;
    }

    public GroupData setGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    public GroupData setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData setGroupHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData setGroupFooter(String footer) {
        this.footer = footer;
        return this;
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

    public Set<ContactData> getContacts() {
        return contacts;
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
    public String toString() {
        return "GroupData{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
