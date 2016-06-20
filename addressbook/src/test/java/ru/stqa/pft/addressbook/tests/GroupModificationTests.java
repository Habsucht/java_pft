/**
 *  A class to test the change of group data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;

public class GroupModificationTests extends BaseTests{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }

        int beforeCount = app.getGroupHelper().getGroupCount();

        app.getGroupHelper().selectGroup(1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();

        int afterCount = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(afterCount, beforeCount);
    }
}
