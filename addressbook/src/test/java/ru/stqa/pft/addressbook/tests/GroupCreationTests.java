/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();
    }

    @Test
    public static void testGroupCreation() {
        Set<GroupData> beforeGroupSet = app.getGroupHelper().getGroupSet();

        GroupData group = new GroupData();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        Set<GroupData> afterGroupSet = app.getGroupHelper().getGroupSet();

        // Check on the number of elements
        Assert.assertEquals(afterGroupSet.size(), beforeGroupSet.size() + 1);

        // Find the maximum Id and assign the created element
        for (GroupData g : afterGroupSet) {
            if (g.getGroupId() > group.getGroupId()) {
                group.setGroupId(g.getGroupId());
            }
        }
        //group.setGroupId(afterGroupSet.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt());

        // Attaching a created element
        beforeGroupSet.add(group);

        // Check elements for identity verification
        Assert.assertEquals(beforeGroupSet, afterGroupSet);
    }
}
