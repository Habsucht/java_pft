/**
 *  A class to test the change of group data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;

import java.util.List;

import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupModificationTests extends BaseTests{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }

        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(generateRandom(beforeGroupList.size()));
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size() + 1);
    }
}
