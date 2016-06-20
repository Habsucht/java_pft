/**
 *  A class to test the group removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTests {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }

        int beforeCount = app.getGroupHelper().getGroupCount();

        app.getGroupHelper().selectGroup(1);
        app.getGroupHelper().deleteGroup();

        app.getNavigationHelper().returnToGroupPage();

        int afterCount = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(afterCount, beforeCount - 1);
    }
}
