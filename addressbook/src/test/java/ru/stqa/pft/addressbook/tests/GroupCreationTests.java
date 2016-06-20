/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

public class GroupCreationTests extends BaseTests {

    @Test
    public static void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();

        int beforeCount = app.getGroupHelper().getGroupCount();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();

        int afterCount = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(afterCount, beforeCount + 1);
    }
}
