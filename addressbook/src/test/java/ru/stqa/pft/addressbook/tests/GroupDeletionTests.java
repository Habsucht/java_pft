/**
 *  A class to test the group removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;

import java.util.List;

import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupDeletionTests extends BaseTests {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }

        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        int index = generateRandom(beforeGroupList.size());

        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().deleteGroup();

        app.getNavigationHelper().returnToGroupPage();

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Check on the number of elements
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size() - 1);

        // Check elements for identity verification
        beforeGroupList.remove(index);
        Assert.assertEquals(beforeGroupList, afterGroupList);
        //for (int i = 0; i < afterGroupList.size(); i++) {
        //    Assert.assertEquals(beforeGroupList.get(i), afterGroupList.get(i));
        //}
    }
}
