/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupCreationTests extends BaseTests {
    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupData()});
        list.add(new Object[] {new GroupData()});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();
    }

    @Test(dataProvider = "validGroups")
    // Base test creation group
    public static void testGroupCreationVer1(GroupData group) {
        Set<GroupData> beforeGroupSet = app.getGroupHelper().getGroupSet();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupSet.size() + 1);

        Set<GroupData> afterGroupSet = app.getGroupHelper().getGroupSet();

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

    @Test(dataProvider = "validGroups")
    // Test creation group fluent implementation
    public static void testGroupCreationVer2(GroupData group) {
        Groups beforeGroupSet = app.getGroupHelper().all();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(beforeGroupSet.size() + 1));

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withAdded(group.setGroupId(afterGroupSet.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    }

    @Test
    // Test creation group with the unacceptable title
    public static void testBadGroupCreationVer1() {
        Set<GroupData> beforeGroupSet = app.getGroupHelper().getGroupSet();

        GroupData group = new GroupData().setGroupName("BadName'");

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupSet.size());

        Set<GroupData> afterGroupSet = app.getGroupHelper().getGroupSet();

        // Check elements for identity verification
        Assert.assertEquals(beforeGroupSet, afterGroupSet);
    }
}
