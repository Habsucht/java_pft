/**
 *  A class to test the group removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
            GroupCreationTests.testGroupCreationVer1();
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

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Check on the number of elements
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size() - 1);

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

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterGroupSet.size(), beforeGroupSet.size() - 1);

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withOut(deletedGroup)));
    }
}
