/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends BaseTests {

    @Test
    public static void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterGroupList.size(), beforeGroupList.size() + 1);
    }
}
