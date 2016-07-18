/**
 *  A class to test the group removal
 */

package ru.stqa.pft.addressbook.tests.ui.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.BaseTests;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupDeletionTests extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreationVer1(new GroupData());
        }
    }

    @Test
    // Base test deletion group
    public void testGroupDeletionVer1() {
        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        int index = generateRandom(beforeGroupList.size());

        app.getGroupHelper().selectGroupByIndex(index);
        app.getGroupHelper().deleteGroup();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupList.size() - 1);

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Removing non-existent element
        beforeGroupList.remove(index);

        // Check elements for identity verification
        Assert.assertEquals(beforeGroupList, afterGroupList);
        /*
        for (int i = 0; i < afterGroupList.size(); i++) {
            Assert.assertEquals(beforeGroupList.get(i), afterGroupList.get(i));
        }
        */
    }

    @Test
    // Test deletion group fluent implementation
    public void testGroupDeletionVer2() {
        Groups beforeGroupSet = app.getGroupHelper().all();

        GroupData deletedGroup = beforeGroupSet.iterator().next();

        app.getGroupHelper().selectGroupById(deletedGroup.getGroupId());
        app.getGroupHelper().deleteGroup();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(beforeGroupSet.size() - 1));

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withOut(deletedGroup)));
    }
}
