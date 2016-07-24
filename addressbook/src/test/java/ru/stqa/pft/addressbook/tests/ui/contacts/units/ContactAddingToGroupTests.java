package ru.stqa.pft.addressbook.tests.ui.contacts.units;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.ui.BaseTests;
import ru.stqa.pft.addressbook.tests.ui.contacts.ContactCreationTests;
import ru.stqa.pft.addressbook.tests.ui.groups.GroupCreationTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class ContactAddingToGroupTests extends BaseTests{
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoHomePage();

        //  Checking the presence of base data at least one contact
        if (app.getDbHelper().contacts().size() == 0) {
            ContactCreationTests.testContactCreationVer1(new ContactData());
        }

        //  Checking the presence of base data at least one group
        if (app.getDbHelper().groups().size() == 0) {
            app.getNavigationHelper().gotoAllGroupsListPage();
            GroupCreationTests.testGroupCreationVer1(new GroupData());
        }
    }

    @Test
    // Test checks add a contact to the group
    public void testContactAddingToGroup() {
        Groups beforeGroupsSet = app.getDbHelper().groups();
        Contacts ContactSet = app.getDbHelper().contacts();

        GroupData modificationGroup = beforeGroupsSet.iterator().next();
        ContactData contactAddToGroup = ContactSet.iterator().next();

        app.getContactHelper().selectContactById(contactAddToGroup.getContactId());
        app.getContactHelper().addSelectContactInGroup(modificationGroup);

        Groups afterGroupsSet = app.getDbHelper().groups();

        // Find modification group in data base
        for (GroupData group : afterGroupsSet) {
            if (group.getGroupId() == modificationGroup.getGroupId()) {
                // Check elements for identity verification
                assertThat(group.getContacts(), hasItem(contactAddToGroup));
            }
        }
    }
}
