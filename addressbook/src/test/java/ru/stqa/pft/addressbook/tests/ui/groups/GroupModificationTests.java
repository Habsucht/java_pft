/**
 *  A class to test the change of group data
 */

package ru.stqa.pft.addressbook.tests.ui.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.ui.BaseTests;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupModificationTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreationVer1(new GroupData());
        }
    }

    @Test
    // Base test modification group
    public void testGroupModificationVer1() {
        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        int index = generateRandom(beforeGroupList.size());
        GroupData group = new GroupData();

        app.getGroupHelper().selectGroupByIndex(index);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupList.size());

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Assign Id the modification element
        group.setGroupId(beforeGroupList.get(index).getGroupId());
        beforeGroupList.remove(index);
        beforeGroupList.add(group);

        // Check elements for identity verification
        Assert.assertEquals(new HashSet<Object>(beforeGroupList), new HashSet<Object>(afterGroupList));
    }

    @Test
    // Test modification group fluent implementation
    public void testGroupModificationVer2() {
        Groups beforeGroupSet = app.getGroupHelper().all();

        GroupData modifiedGroup = beforeGroupSet.iterator().next();
        GroupData group = new GroupData();

        app.getGroupHelper().selectGroupById(modifiedGroup.getGroupId());
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(beforeGroupSet.size()));

        Groups afterGroupSet = app.getGroupHelper().all();

        // Assign Id the modification element
        group.setGroupId(modifiedGroup.getGroupId());

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withOut(modifiedGroup).withAdded(group)));
    }
}
