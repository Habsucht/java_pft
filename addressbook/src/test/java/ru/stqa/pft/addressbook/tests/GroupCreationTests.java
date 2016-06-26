/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends BaseTests {

    @Test
    public static void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Check on the number of elements
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size() + 1);

        // Check elements for identity verification
        for (GroupData g : afterGroupList) {
            if (g.getGroupId() > group.getGroupId()) {
                group.setGroupId(g.getGroupId());
            }
        }
        Assert.assertEquals(new HashSet<Object>(beforeGroupList), new HashSet<Object>(afterGroupList));
    }
}
