/**
 *  A class to test the change of group data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;

import java.util.HashSet;
import java.util.List;

import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupModificationTests extends BaseTests{
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        int index = generateRandom(beforeGroupList.size());
        GroupData group = new GroupData();

        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

        // Check on the number of elements
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size());

        // Assign Id the modification element
        group.setGroupId(beforeGroupList.get(index).getGroupId());
        beforeGroupList.remove(index);
        beforeGroupList.add(group);

        // Check elements for identity verification
        Assert.assertEquals(new HashSet<Object>(beforeGroupList), new HashSet<Object>(afterGroupList));
    }
}
